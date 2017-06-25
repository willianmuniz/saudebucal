package br.com.willmo.saudebucal.tools;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by @WillianMuniz on 7/7/2016.
 */
public class SqliteUtils {

    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT = "yyyy-MM-dd";

    public static DateTimeFormatter getDateTimeFormatter() {
        return DateTimeFormat.forPattern(SqliteUtils.DATETIME_FORMAT);
    }
    public static DateTimeFormatter getDateFormatter() {
        return DateTimeFormat.forPattern(SqliteUtils.DATE_FORMAT);
    }
    public static DateTimeFormatter getDateFormatter(String format) {
        return DateTimeFormat.forPattern(format);
    }

    public static String getString(String txt) {
        return txt != null ? txt : "";
    }

}
