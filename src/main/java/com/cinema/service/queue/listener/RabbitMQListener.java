package com.cinema.service.queue.listener;

import com.cinema.service.dto.TicketDTO;
import com.cinema.service.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.cinema.service.queue.QueueHandler.handleQueue;

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
