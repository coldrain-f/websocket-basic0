package me.websocket.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
@ServerEndpoint(value = "/chatt")
public class WebSocketChatt {

    private static final Set<Session> clients =
            Collections.synchronizedSet(new HashSet<Session>());


    @OnOpen
    public void onOpen(Session session) {
        log.info("open session : " + session.toString());
        if (!clients.contains(session)) {
            clients.add(session);
            log.info("session open : " + session);
        } else {
            log.info("이미 연결된 session 입니다.");
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) throws Exception {
        log.info("receive message : " + message);
        for (Session s : clients) {
            log.info("send data : " + message);
            s.getBasicRemote().sendText(message);
        }
    }

    @OnClose
    public void onClose(Session session) {
        log.info("session close : " + session);
        clients.remove(session);
    }
}