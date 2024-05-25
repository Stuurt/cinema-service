package com.cinema.service.domain.queue.listener;

import com.cinema.service.rest.dto.TicketDTO;
import com.cinema.service.domain.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.cinema.service.domain.queue.QueueHandler.handleQueue;

@Component
@RequiredArgsConstructor
public class RabbitMQListener {

    private final TicketService ticketService;

    @RabbitListener(queues = "ticketQueue")
    public void onMessage(Message ticketMessage) {
        var ticket = handleQueue(ticketMessage, TicketDTO.class);
        ticketService.createTicket(ticket);
    }
}
