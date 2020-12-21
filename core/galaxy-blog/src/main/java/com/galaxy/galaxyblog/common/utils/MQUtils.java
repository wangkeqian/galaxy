//package com.galaxy.galaxyblog.common.utils;
//
//import com.alibaba.fastjson.JSONObject;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
///**
// * TODO 请说明此类的作用
// *
// * @author wangkq
// * @date 2020/12/18
// */
//@Component
//public class MQUtils {
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//    public void send(String exchange, String queue, Object o){
//        String obj = JSONObject.toJSONString(o);
//        rabbitTemplate.convertAndSend(exchange, queue, obj);
//    }
//}
