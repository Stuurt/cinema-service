package com.cinema.service.queue.listener;

import com.cinema.service.dto.TicketDTO;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.cinema.service.queue.QueueHandler.handleQueue;

@Component
public class RabbitMQListener {
    @RabbitListener(queues = "ticketQueue")
    public void listen(Message ticketMessage) {
        handleQueue(ticketMessage, TicketDTO.class);
    }
}
