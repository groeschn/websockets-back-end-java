package de.groeschn.javaee.websocket;

import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.websocket.*;

public class TimerInfoCoder implements Decoder.Text<TimerInfoMessage>, Encoder.Text<TimerInfoMessage> {

    @Inject
    private Jsonb jsonb;

    @Override
    public TimerInfoMessage decode(String s) throws DecodeException {
        return this.jsonb.fromJson(s, TimerInfoMessage.class);
    }

    @Override
    public boolean willDecode(String s) {
        return true;
    }

    @Override
    public String encode(TimerInfoMessage timerInfoMessage) throws EncodeException {
        return this.jsonb.toJson(timerInfoMessage);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
    }

    @Override
    public void destroy() {
    }
}
