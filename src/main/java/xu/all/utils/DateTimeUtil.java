package xu.all.utils;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.DateTimeParser;

public class DateTimeUtil {

    static {
        // set time zone
        DateTimeZone.setDefault(DateTimeZone.forOffsetHours(8));
    }

    public static final DateTimeFormatter ISO8601DateTimeformatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    public static final DateTimeFormatter ISO8601DateTimeformatterNoMS = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ssZ");
    public static final DateTimeFormatter UTCDateTimeformatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss zzz");
    public static final DateTimeFormatter standardDateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");
    public static final DateTimeFormatter dateFormatterNoDash = DateTimeFormat.forPattern("yyyyMMdd");

    private static final DateTimeParser[] dateTimeParsers = {
            ISO8601DateTimeformatter.getParser(),
            ISO8601DateTimeformatterNoMS.getParser(),
            UTCDateTimeformatter.getParser(),
            standardDateTimeFormatter.getParser(),
            dateFormatter.getParser(),
            dateFormatterNoDash.getParser()
    };

    private static final DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder().append(null, dateTimeParsers).toFormatter();

    public static DateTime parseDateTime(String text) {
        return dateTimeFormatter.parseDateTime(text);
    }

}
