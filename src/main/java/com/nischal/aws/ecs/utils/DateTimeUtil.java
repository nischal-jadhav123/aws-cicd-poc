package com.nischal.aws.ecs.utils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class DateTimeUtil {

    public static LocalDateTime calculateRange(LocalDateTime currentTime, int amount, String chronoUnit) {
        switch (chronoUnit){
            case "mins" : return currentTime.minus(amount, ChronoUnit.MINUTES);
            case "days" : return currentTime.minus(amount, ChronoUnit.DAYS);
            case "months" : return currentTime.minus(amount, ChronoUnit.MONTHS);
            case "years" : return currentTime.minus(amount,ChronoUnit.YEARS);
            default: return currentTime.minus(amount, ChronoUnit.HOURS);
        }

    }


}
