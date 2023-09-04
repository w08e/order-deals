package com.zhouyi.order.infrastructure.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * rabbitMq配置
 */
@Configuration
public class RabbitMqConfig {

    @Bean
    public Queue delCacheQueue() {
        return new Queue("delCache");
    }

    @Bean
    public Queue orderQueue() {
        return new Queue("orderQueue");
    }

}
