package ca.bc.gov.open.jag.pac.poller.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RabbitMqConfig {

    private final QueueConfig queueConfig;

    @Value("${spring.rabbitmq.host}")
    private String rabbitHost;

    @Autowired
    public RabbitMqConfig(QueueConfig queueConfig) {
        this.queueConfig = queueConfig;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean(name = "pac-queue")
    public Queue pacQueue() {
        return new Queue(queueConfig.getPacQueueName(), false);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(queueConfig.getTopicExchangeName());
    }

    @Bean
    public AmqpAdmin amqpAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public Declarables binding(@Qualifier("pac-queue") Queue pacQueue, DirectExchange exchange) {
        return new Declarables(
                BindingBuilder.bind(pacQueue).to(exchange).with(queueConfig.getPacRoutingkey()));
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory =
                new CachingConnectionFactory(rabbitHost);
        return connectionFactory;
    }
    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }
}
