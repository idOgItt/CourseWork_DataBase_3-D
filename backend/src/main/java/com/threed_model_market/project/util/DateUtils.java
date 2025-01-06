package com.threed_model_market.project.util;

import java.util.Date;

public class DateUtils {

    public static Date addDaysToDate(Date date, int daysToAdd) {
        long time = date.getTime();
        long newTime = time + (daysToAdd * 24 * 60 * 60 * 1000L);
        return new Date(newTime);
    }
}
