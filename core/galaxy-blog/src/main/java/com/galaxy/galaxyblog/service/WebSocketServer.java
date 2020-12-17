package com.galaxy.galaxyblog.service;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * TODO 请说明此类的作用
 *
 * @author wangkq
 * @date 2020/12/16
 */
@ServerEndpoint(value = "/ws/{uid}")
@Component
public class WebSocketServer {

    private static final AtomicInteger onlineCount = new AtomicInteger(0);
    private static final ConcurrentHashMap sessionPool = new ConcurrentHashMap();


    //@PostConstruct该注解被用来修饰一个非静态的void（）方法。
    // 被@PostConstruct修饰的方法会在服务器加载Servlet的时候运行，并且只会被服务器执行一次。
    // PostConstruct在构造函数之后执行，init（）方法之前执行。
    @PostConstruct
    public void init() {
        System.out.println("websocket 加载");
    }
    @SneakyThrows
    public static void sendMessage(Session session, String msg) {
        if (session != null){
            synchronized (session){
                System.out.println("发送数据: "+msg);
                session.getBasicRemote().sendText(msg);
            }
        }
    }
    public static void sendInfo2Client(String uid ,String msg){
        Session s = (Session) sessionPool.get(uid);
        if (s != null){
            sendMessage(s, msg);
        }
    }
    @OnOpen
    public void onOpen(Session session, @PathParam(value = "uid")String uid){
        sessionPool.put(uid, session);
        addOnlineCount();
        System.out.println(uid + "加入webSocket！当前人数为" + onlineCount);
        sendInfo2Client(uid,"欢迎"+uid+"加入");
    }
    @OnMessage
    public void onMessage(String message, Session session){
        message = "收到来自客户端的信息\""+message+"\"";
        sendMessage(session, message);
    }
    @OnError
    public void onError(Session s, Throwable err){
        System.out.println("发生错误，「 Session ID 」"+s.getId()+" - "+err.getMessage());
        err.printStackTrace();
    }
    @OnClose
    public void onClose(@PathParam(value = "uid") String uid){
        sessionPool.remove(uid);
        subOnlineCount();
        System.out.println("断开连接，目前在线"+onlineCount+"个链接");
    }


    private void addOnlineCount(){
        onlineCount.incrementAndGet();
    }
    private void subOnlineCount(){
        onlineCount.decrementAndGet();
    }
}
