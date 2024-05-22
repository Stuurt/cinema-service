package com.cinema.service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    @Id
    private Long id;
    private Integer roomNumber;
    private Integer totalSeats;
    @OneToMany(mappedBy = "room", cascade = CascadeType.REMOVE)
    private List<Seat> seats;
}
