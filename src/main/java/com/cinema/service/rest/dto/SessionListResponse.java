package com.cinema.service.rest.dto;

import com.cinema.service.domain.entity.Movie;
import com.cinema.service.domain.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SessionListResponse {
    private Long id;
    private LocalDateTime sessionStartTime;
    private LocalDateTime sessionEndTime;
    private BigDecimal basePrice;
    private Movie movie;
    private Room room;
}
