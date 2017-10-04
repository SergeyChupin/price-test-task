package ru.csi.util;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class TimeUtil {

    public static Instant date(
            int year,
            int month,
            int dayOfMonth) {
        return date(year, month, dayOfMonth, 0, 0, 0);
    }

    public static Instant date(
            int year,
            int month,
            int dayOfMonth,
            int hour,
            int minute,
            int second) {
        return ZonedDateTime.of(year, month, dayOfMonth, hour, minute, second, 0, ZoneOffset.UTC)
                .toInstant();
    }

    public static boolean beforeAndEquals(Instant a, Instant b) {
        return a.isBefore(b) || a.equals(b);
    }

    public static boolean afterAndEquals(Instant a, Instant b) {
        return a.isAfter(b) || a.equals(b);
    }
}
