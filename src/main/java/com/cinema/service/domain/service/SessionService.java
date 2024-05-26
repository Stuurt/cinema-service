package com.cinema.service.domain.service;

import com.cinema.service.domain.entity.Movie;
import com.cinema.service.domain.entity.Room;
import com.cinema.service.domain.entity.Seat;
import com.cinema.service.domain.entity.Session;
import com.cinema.service.domain.enums.SeatTypeEnum;
import com.cinema.service.rest.dto.CreateSessionRequest;
import com.cinema.service.domain.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionService {
    private final SessionRepository sessionRepository;
    private final RoomService roomService;
    private final MovieService movieService;

    @Value("${cinema.max-session-hours}")
    private Integer maxSessionHours;
    @Value("${cinema.min-session-minutes}")
    private Integer minimumSessionMinutes;

    public Session createSession(CreateSessionRequest session, Long movieId, Long roomId) {
        Movie movie = movieService.findById(movieId);
        Room room = roomService.findById(roomId);
        List<Seat> seats = createSeatsForSession(room.getTotalSeats());

        validateRoomAvailability(session.getSessionStartTime(), session.getSessionEndTime(), roomId);
        validateSessionDuration(session);

        return sessionRepository.save(
                new Session(
                        null,
                        session.getSessionStartTime(),
                        session.getSessionEndTime(),
                        session.getBasePrice(),
                        movie,
                        room,
                        seats
                ));
    }

    public Session findById(Long sessionId) {
        return sessionRepository.findById(sessionId)
                .orElseThrow(() -> new IllegalArgumentException("session with id: [" + sessionId + "] not found"));
    }

    private void validateRoomAvailability(
            LocalDateTime sessionStartTime,
            LocalDateTime sessionEndTime,
            Long roomId) {

        Boolean roomIsEmptyAtThisTime = sessionRepository.isRoomEmptyAtThisTime(sessionStartTime, sessionEndTime, roomId);
        if (!roomIsEmptyAtThisTime)
            throw new IllegalArgumentException("this room already have a session at this time period");
    }

    private void validateSessionDuration(CreateSessionRequest session) {
        LocalDateTime sessionEndTime = session.getSessionEndTime();
        LocalDateTime sessionStartTimePlusLimit = session.getSessionStartTime().plusHours(maxSessionHours);

        Boolean exceedsSessionTimeLimit = sessionEndTime.isAfter(sessionStartTimePlusLimit)
                || sessionEndTime.isEqual(sessionStartTimePlusLimit);

        if (exceedsSessionTimeLimit) {
            throw new IllegalArgumentException("session has exceeded the time limit. if this is intentional and not an error, please contact the administrator");
        }

        LocalDateTime sessionTimePlusMinimum = session.getSessionStartTime().plusMinutes(minimumSessionMinutes);

        Boolean lessThanMinimumSessionTimeAllowed = sessionEndTime.isBefore(sessionTimePlusMinimum);
        if (lessThanMinimumSessionTimeAllowed) {
            throw new IllegalArgumentException("session duration is less than the minimum required. please ensure the session duration is at least 10 minutes");
        }
    }

    private List<Seat> createSeatsForSession(Integer totalSeats) {
        List<Seat> seats = new ArrayList<>();
        for (int i = 1; i <= totalSeats; i++) {
            Seat seat = new Seat();
            seat.setSeatNumber(i);
            seat.setAvailable(true);
            seat.setType(SeatTypeEnum.STANDARD);
            seats.add(seat);
        }
        return seats;
    }
}
