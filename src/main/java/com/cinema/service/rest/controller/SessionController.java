package com.cinema.service.rest.controller;

import com.cinema.service.domain.entity.Session;
import com.cinema.service.rest.dto.CreateSessionRequest;
import com.cinema.service.domain.service.SessionService;
import com.cinema.service.rest.dto.SessionResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sessions")
@RequiredArgsConstructor
@Validated
public class SessionController {
    private final SessionService sessionService;

    @PostMapping("/movies/{movieId}/rooms/{roomId}")
    public ResponseEntity<Session> createSession(
            @Valid @RequestBody CreateSessionRequest createSessionRequest,
            @PathVariable Long movieId,
            @PathVariable Long roomId
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(sessionService.createSession(createSessionRequest, movieId, roomId));
    }

    @GetMapping("/{sessionId}")
    public ResponseEntity<SessionResponse> getSessionById(@PathVariable Long sessionId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(sessionService.findById(sessionId));
    }
}
