package de.groeschn.javaee.websocket;

import de.groeschn.javaee.model.TimerInfo;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint(value = "/echo", decoders = {TimerInfoCoder.class},
        encoders = {TimerInfoCoder.class})
public class WebsocketEndpoint {

    @Inject
    private Logger log;

    @OnOpen
    public void onOpen(Session session, EndpointConfig conf) {
        log.info("onOpen called");
    }

    @OnMessage
    public void onMessage(Session session, TimerInfo message) {
        log.info("onMessage called with " + message.toString());
        try {
            session.getBasicRemote().sendText("ok");
            session.getBasicRemote().sendObject(message);
        } catch (Exception e) {
            log.error("", e);
        }
    }

    @OnClose
    public void onClose(Session session, CloseReason reason) {
        log.info("onClose called");
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.info("onError called", error);
        try {
            session.getBasicRemote().sendText("error happened");
        } catch (IOException e) {
            log.error("", e);
        }
    }
}
