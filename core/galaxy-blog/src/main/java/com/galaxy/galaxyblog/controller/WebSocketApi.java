package com.galaxy.galaxyblog.controller;


import com.galaxy.galaxyblog.common.HttpResultResp;
import com.galaxy.galaxyblog.common.WsResultResp;
import com.galaxy.galaxyblog.service.WebSocketServer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO 请说明此类的作用
 *
 * @author wangkq
 * @date 2020/12/16
 */
@RestController
@RequestMapping("/ws")
public class WebSocketApi {


    @GetMapping("/sendOne")
    public void sendOne(@RequestParam(required = true) String uid,
                          @RequestParam(required = true)String message){
        WebSocketServer.sendInfo2Client(uid, WsResultResp.QUIET(message));
    }
}
