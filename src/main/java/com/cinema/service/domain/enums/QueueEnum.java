package com.cinema.service.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum QueueEnum {

    TICKET_QUEUE("ticketQueue");

    private final String queueName;
}