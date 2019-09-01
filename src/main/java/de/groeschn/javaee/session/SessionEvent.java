package de.groeschn.javaee.session;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.websocket.Session;

@Data
@AllArgsConstructor
public class SessionEvent {
    private SessionEventAction action;
    private Session session;
}
