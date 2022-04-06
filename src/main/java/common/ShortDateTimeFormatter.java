package common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ShortDateTimeFormatter {
    private static final String pattern = "MM.dd hh:mm";
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);

    public static String format(LocalDateTime dateTime) {
        return dateTimeFormatter.format(dateTime);
    }
}
