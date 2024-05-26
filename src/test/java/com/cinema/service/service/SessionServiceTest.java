package com.cinema.service.service;

import com.cinema.service.domain.entity.Movie;
import com.cinema.service.domain.entity.Room;
import com.cinema.service.domain.repository.SessionRepository;
import com.cinema.service.domain.service.MovieService;
import com.cinema.service.domain.service.RoomService;
import com.cinema.service.domain.service.SessionService;
import com.cinema.service.rest.dto.CreateSessionRequest;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class SessionServiceTest {
    @InjectMocks
    private SessionService sessionService;
    @Mock
    private MovieService movieService;
    @Mock
    private RoomService roomService;
    @Mock
    private SessionRepository sessionRepository;

    private Integer maxSessionHours = 5;
    private Integer minimumSessionMinutes = 30;

    @Test
    public void givenCreatingNewSession_whenSessionTimeConflicts_thenShouldThrow() {
        var movie = buildMovieExample();
        var room = buildRoomExample();

        when(movieService.findById(anyLong()))
                .thenReturn(movie);

        when(roomService.findById(anyLong()))
                .thenReturn(room);

        when(sessionRepository.isRoomEmptyAtThisTime(any(), any(), anyLong()))
                .thenReturn(false);

        CreateSessionRequest session = new CreateSessionRequest(LocalDateTime.now().plusHours(5), LocalDateTime.now().plusHours(4), BigDecimal.TWO);

        assertThrows(IllegalArgumentException.class, () ->
                sessionService.createSession(session, 1L, 1L));
    }

    @Test
    public void givenCreatingNewSession_whenSessionMoreThanMax_thenShouldThrow() {
        var movie = buildMovieExample();
        var room = buildRoomExample();

        ReflectionTestUtils.setField(sessionService, "maxSessionHours", maxSessionHours);
        ReflectionTestUtils.setField(sessionService, "minimumSessionMinutes", minimumSessionMinutes);

        when(movieService.findById(anyLong()))
                .thenReturn(movie);

        when(roomService.findById(anyLong()))
                .thenReturn(room);

        when(sessionRepository.isRoomEmptyAtThisTime(any(), any(), anyLong()))
                .thenReturn(true);

        CreateSessionRequest session = new CreateSessionRequest(LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(maxSessionHours + 1), BigDecimal.TWO);

        assertThrows(IllegalArgumentException.class, () ->
                sessionService.createSession(session, 1L, 1L));
    }

    public Movie buildMovieExample() {
        return new Movie(
                1L,
                "Test Movie",
                "A very adult film",
                "18", "Adult Film",
                LocalDate.now(), "1H30M",
                "Test Director",
                "Actor One, Actor Two",
                "Test Producer"
        );
    }

    public Room buildRoomExample() {
        return new Room(
                1L,
                1,
                40
        );
    }
}
