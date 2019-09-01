package de.groeschn.javaee.session;

import de.groeschn.javaee.websocket.TimerInfoMessage;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.enterprise.event.Observes;
import javax.websocket.Session;
import java.util.LinkedList;
import java.util.List;

@Singleton
@Slf4j
public class SessionManager {

    private List<Session> sessions;

    @PostConstruct
    private void init() {
        this.sessions = new LinkedList<>();
    }

    public void handleEvent(@Observes SessionEvent sessionEvent) {
        switch (sessionEvent.getAction()) {
            case ADD:
                addSession(sessionEvent.getSession());
                break;
            case REMOVE:
                removeSession(sessionEvent.getSession());
                break;
        }
    }

    private void addSession(Session session) {
        sessions.add(session);
    }

    private void removeSession(Session session) {
        sessions.remove(session);
    }

    public void sendBroadcast(TimerInfoMessage message) {
        sessions.forEach(session -> {
            try {
                session.getBasicRemote().sendObject(message);
            } catch (Exception e) {
                log.error("error during broadcast", e);
            }
        });
    }

}
