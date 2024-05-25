package com.cinema.service.domain.queue.configuration;

import com.cinema.service.domain.enums.QueueEnum;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {
    @Bean
    public Queue ticketQueue() {
        return new Queue(QueueEnum.TICKET_QUEUE.getQueueName(), true);
    }
}