package de.groeschn.javaee.service;

import de.groeschn.javaee.model.TimerInfo;
import de.groeschn.javaee.session.SessionManager;
import de.groeschn.javaee.websocket.TimerInfoMessageAction;
import de.groeschn.javaee.websocket.TimerInfoMessage;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.inject.Inject;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Singleton
public class TimerInfoService {

    @Inject
    private SessionManager sessionManager;

    private List<TimerInfo> timerInfos;

    @PostConstruct
    public void init() {
        this.timerInfos = new LinkedList<>();
    }

    public void addTimerInfo(Collection<TimerInfo> timerInfos) {
        this.timerInfos.removeAll(timerInfos);
        this.timerInfos.addAll(timerInfos);
        this.sessionManager.sendBroadcast(listAllMessage());
    }

    public void removeTimerInfo(Collection<TimerInfo> timerInfos) {
        this.timerInfos.removeAll(timerInfos);
        this.sessionManager.sendBroadcast(listAllMessage());
    }

    public TimerInfoMessage listAllMessage() {
        return new TimerInfoMessage(TimerInfoMessageAction.LIST, this.timerInfos);
    }

}
