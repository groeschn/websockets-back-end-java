package de.groeschn.javaee.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigInteger;
import java.time.ZonedDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TimerInfo {

    @EqualsAndHashCode.Include
    private String id;
    private String name;
    private BigInteger accumulatedMilliseconds;
    private ZonedDateTime start;

}
