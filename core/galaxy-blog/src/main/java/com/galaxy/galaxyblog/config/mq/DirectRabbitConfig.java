//package com.galaxy.galaxyblog.config.mq;
//
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.DirectExchange;
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.core.RedisTemplate;
//
///**
// * TODO 请说明此类的作用
// *
// * @author wangkq
// * @date 2020/12/18
// */
//@Configuration
//public class DirectRabbitConfig {
//
//    @Bean
//    public ConnectionFactory connectionFactory() {
//        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
//        cachingConnectionFactory.setAddresses("112.74.161.190:5672");
//        cachingConnectionFactory.setUsername("root");
//        cachingConnectionFactory.setPassword("123456");
//        return cachingConnectionFactory;
//    }
//
//    @Bean
//    RabbitTemplate rabbitTemplate(){
//        System.out.println("rabbitTemplate 实例化中");
//        return new RabbitTemplate(connectionFactory());
//    }
//
//    @Bean
//    Queue noticeQueue(){
//        // durable:是否持久化,默认是false,持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
//        // exclusive:默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
//        // autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
//        //   return new Queue("TestDirectQueue",true,true,false);
//
//        return new Queue("noticeQueue",true);
//    }
//
//    @Bean
//    DirectExchange directExchange(){
//        return new DirectExchange("noticeExchange",true,false);
//    }
//
//    @Bean
//    Binding bindingDirect(){
//        return BindingBuilder.bind(noticeQueue()).to(directExchange()).with("push_notice");
//    }
//    @Bean
//    DirectExchange lonelyDirectExchange() {
//        return new DirectExchange("lonelyDirectExchange");
//    }
//}
