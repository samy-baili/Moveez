package com.moviedb.android.moviedb.util;

import android.support.annotation.StringDef;

import com.moviedb.android.moviedb.common.StaticVariables;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    /**
     * Convert a String date in Date java object
     *
     * @param date The date to convert
     * @param format The format of the date to convert
     * @return The date in java Date format
     */
    public static Date convertToDate(String date, @DATE_FORMAT_ENUM String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.getDefault());
        try {
            return simpleDateFormat.parse(date);
        }
        catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Convert the date to a specific style, using DateFormat stylePattern
     *
     * @param date Date to convert
     * @param stylePattern The style applied to the date {SHORT, MEDIUM, LONG, FULL, ...}
     * @return The date stylized to a specific pattern
     */
    public static String formatDate(Date date, int stylePattern) {
        return DateFormat.getDateInstance(stylePattern).format(date);
    }

    @StringDef({
            StaticVariables.DEFAULT_DATE_FORMAT
    })
    @Retention(RetentionPolicy.SOURCE)
    private @interface DATE_FORMAT_ENUM {}
}
