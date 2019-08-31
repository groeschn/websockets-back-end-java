package de.groeschn.javaee.websocket;

import de.groeschn.javaee.model.TimerInfo;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.websocket.*;

public class TimerInfoCoder implements Decoder.Text<TimerInfo>, Encoder.Text<TimerInfo> {

    @Inject
    private Logger log;

    @Inject
    private Jsonb jsonb;

    @Override
    public TimerInfo decode(String s) throws DecodeException {
        return this.jsonb.fromJson(s, TimerInfo.class);
    }

    @Override
    public boolean willDecode(String s) {
        return true;
    }

    @Override
    public String encode(TimerInfo timerInfo) throws EncodeException {
        return this.jsonb.toJson(timerInfo);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
    }

    @Override
    public void destroy() {
    }
}
