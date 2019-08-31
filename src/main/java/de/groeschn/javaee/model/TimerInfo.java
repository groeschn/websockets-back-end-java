package de.groeschn.javaee.model;

import lombok.Data;

import java.math.BigInteger;
import java.time.ZonedDateTime;

@Data
public class TimerInfo {

    private String id;
    private String name;
    private BigInteger accumulatedMilliseconds;
    private ZonedDateTime start;

}
