package com.cinema.service.rest.dto;

import com.cinema.service.domain.enums.SeatTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class SeatResponse {
    private Long id;
    private Integer seatNumber;
    private Boolean available;
    @Enumerated(EnumType.STRING)
    private SeatTypeEnum type;
}
