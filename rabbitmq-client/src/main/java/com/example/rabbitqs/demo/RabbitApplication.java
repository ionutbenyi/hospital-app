package com.example.rabbitqs.demo;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RabbitApplication {

    private static final String EXCHANGE_NAME = "tips_tx";
    private static final String DEFAULT_PARSING_QUEUE = "default_parser_q";
    private static final String ROUTING_KEY = "tips";

    @Bean
    public TopicExchange appExchange(){
        return new TopicExchange((EXCHANGE_NAME));
    }

    @Bean
    public Queue appQueueGeneric(){
        return new Queue("generic_queue");
    }

    @Bean
    public Queue appQueueSpecific(){
        return new Queue("specific_queue");
    }

    @Bean
    public Binding declareBindingGeneric(){
        return BindingBuilder.bind(appQueueGeneric()).to(appExchange()).with(ROUTING_KEY);
    }

    @Bean
    public Binding declareBindingSpecific(){
        return BindingBuilder.bind(appQueueSpecific()).to(appExchange()).with(ROUTING_KEY);
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory){
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(RabbitApplication.class, args);
    }

}

