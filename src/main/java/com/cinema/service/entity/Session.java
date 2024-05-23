package com.cinema.service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Session {
    @Id
    private Long id;
    private LocalDateTime sessionTime;
    private BigDecimal basePrice;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "movie_id")
    private Movie movie;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id")
    private Room room;
    @OneToMany(mappedBy = "session", fetch = FetchType.EAGER)
    private List<Seat> seats;
}