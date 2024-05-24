package com.cinema.service.domain.service;

import com.cinema.service.domain.entity.Movie;
import com.cinema.service.domain.entity.Room;
import com.cinema.service.domain.entity.Seat;
import com.cinema.service.domain.entity.Session;
import com.cinema.service.domain.enums.SeatTypeEnum;
import com.cinema.service.rest.dto.CreateSessionRequest;
import com.cinema.service.domain.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionService {
    private final SessionRepository sessionRepository;
    private final RoomService roomService;
    private final MovieService movieService;

    public Session createSession(CreateSessionRequest session, Long movieId, Long roomId) {
        Movie movie = movieService.findById(movieId);
        Room room = roomService.findById(roomId);
        List<Seat> seats = createSeatsForSession(room.getTotalSeats());

        return sessionRepository.save(
                new Session(
                        null,
                        session.getSessionTime(),
                        session.getBasePrice(),
                        movie,
                        room,
                        seats
                ));
    }

    private List<Seat> createSeatsForSession(Integer totalSeats) {
        List<Seat> seats = new ArrayList<>();
        for (int i = 1; i <= totalSeats; i++) {
            Seat seat = new Seat();
            seat.setSeatNumber(i);
            seat.setStatus(true);
            seat.setType(SeatTypeEnum.NORMAL);
            seats.add(seat);
        }
        return seats;
    }
}
