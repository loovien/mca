package test;

import java.time.Duration;
import java.time.Instant;
import java.time.chrono.ChronoPeriod;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;

public class TimeTest {
    public static void main(String[] args) {
        Instant now = Instant.now();
        long l = System.currentTimeMillis();
        Instant now2 = Instant.now().plusSeconds(100);
        System.out.println(Duration.between(now, now2).getNano());
        System.out.println(ChronoUnit.SECONDS.between(now, now2));
        System.out.println(l);
    }
}
