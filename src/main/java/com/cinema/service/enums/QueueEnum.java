package com.cinema.service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum QueueEnum {

    TICKET_QUEUE("ticketQueue");

    private final String queueName;
}