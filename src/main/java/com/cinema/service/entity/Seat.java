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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id")
    private Session session;
    @OneToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;
}
