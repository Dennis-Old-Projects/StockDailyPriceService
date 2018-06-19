package com.multiplan.stockdaily.events;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;

public class StockEventProducer {

	@Autowired
    private RabbitTemplate template;

    @Autowired
    private Queue queue;
    
    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    
    public void send(String event) {       
        this.template.convertAndSend(queue.getName(), event);
        LOGGER.info(" [x] Sent '" + event + "'");
    }
}
