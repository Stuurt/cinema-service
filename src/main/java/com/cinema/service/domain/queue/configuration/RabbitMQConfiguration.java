package com.cinema.service.domain.queue.configuration;

import com.cinema.service.domain.enums.QueueEnum;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {
    @Bean
    public Queue ticketQueue() {
        return QueueBuilder.durable(QueueEnum.TICKET_QUEUE.getQueueName())
                .withArgument("x-dead-letter-exchange", "")
                .withArgument("x-dead-letter-routing-key", "ticketDeadLetter")
                .build();
    }

    @Bean
    Queue deadLetterQueue() {
        return QueueBuilder.durable("ticketDeadLetter").build();
    }

    @Bean
    DirectExchange messagesExchange() {
        return new DirectExchange("exchangeQueue");
    }

    @Bean
    Binding bindingMessages() {
        return BindingBuilder.bind(ticketQueue()).to(messagesExchange()).with(QueueEnum.TICKET_QUEUE.getQueueName());
    }
}