package de.groeschn.javaee.websocket;

import de.groeschn.javaee.service.TimerInfoService;
import de.groeschn.javaee.session.SessionEvent;
import de.groeschn.javaee.session.SessionEventAction;
import de.groeschn.javaee.session.SessionManager;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@Slf4j
@ServerEndpoint(value = "/timer-info", decoders = {TimerInfoCoder.class},
        encoders = {TimerInfoCoder.class})
public class WebsocketEndpoint {

    @Inject
    private Event<SessionEvent> eventSource;
    @Inject
    private TimerInfoService timerInfoService;

    @OnOpen
    public void onOpen(Session session, EndpointConfig conf) {
        log.info("onOpen called");
        eventSource.fire(new SessionEvent(SessionEventAction.ADD, session));
        try {
            session.getBasicRemote().sendObject(timerInfoService.listAllMessage());
        } catch (Exception e) {
            log.error("", e);
        }
    }

    @OnMessage
    public void onMessage(Session session, TimerInfoMessage message) {
        log.info("onMessage called with " + message.toString());
        switch (message.getAction()) {
            case ADD:
                timerInfoService.addTimerInfo(message.getTimerInfos());
                break;
            case REMOVE:
                timerInfoService.removeTimerInfo(message.getTimerInfos());
                break;
            case LIST:
                try {
                    session.getBasicRemote().sendObject(timerInfoService.listAllMessage());
                } catch (Exception e) {
                    log.error("", e);
                }
                break;
            default:
                try {
                    session.getBasicRemote().sendText("error happened");
                } catch (IOException e) {
                    log.error("Error during sending text", e);
                }
                break;
        }
    }

    @OnClose
    public void onClose(Session session, CloseReason reason) {
        log.info("onClose called");
        eventSource.fire(new SessionEvent(SessionEventAction.REMOVE, session));
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
