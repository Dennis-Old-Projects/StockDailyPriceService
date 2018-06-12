package com.multiplan.stockdaily.events;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.amqp.core.Queue;

public class StockEventProducer {

	@Autowired
    private RabbitTemplate template;

    @Autowired
    private Queue queue;
    
    public void send(String event) {       
        this.template.convertAndSend(queue.getName(), event);
        System.out.println(" [x] Sent '" + event + "'");
    }
}
