//package com.galaxy.galaxyblog.config.mq;
//
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
///**
// * TODO 请说明此类的作用
// *
// * @author wangkq
// * @date 2020/12/18
// */
//@Component
//@RabbitListener(queues = "noticeQueue")
//public class DirectReceiver {
//
//
//    @RabbitHandler
//    public void process(String testMessage) {
//        System.out.println("DirectReceiver消费者收到消息  : " + testMessage.toString());
//    }
//}
