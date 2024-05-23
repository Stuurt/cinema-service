package com.cinema.service.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Seat {
    @Id
    private Long id;
    private Integer seatNumber;
    private Boolean status;
}
