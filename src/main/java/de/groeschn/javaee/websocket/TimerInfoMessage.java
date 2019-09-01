package de.groeschn.javaee.websocket;

import de.groeschn.javaee.model.TimerInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimerInfoMessage {

    private TimerInfoMessageAction action;
    private List<TimerInfo> timerInfos;

}
