package com.multiplan.stockdaily.rest.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.multiplan.stockdaily.events.StockEventConsumer;
import com.multiplan.stockdaily.events.StockEventProducer;

@Configuration
public class RabbitConfig {

	static final String queueName = "stockEventQueue";
		
    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate templete=new RabbitTemplate();
        templete.setConnectionFactory(connectionFactory);
        return templete;
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(listenerAdapter);
        return container;
    }
    
    @Bean
    public Queue stockEventQueue() {
        return new Queue("stockEventQueue");
    }
    
    @Bean StockEventProducer stockEventProducer() {
 	   return new StockEventProducer();
    }
    
    @Bean StockEventConsumer stockEventConsumer() {
  	   return new StockEventConsumer();
    }
    
    @Bean
    MessageListenerAdapter listenerAdapter(StockEventConsumer stockEventConsumer) {
        return new MessageListenerAdapter(stockEventConsumer, "receive");
    }
}
