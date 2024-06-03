package io.codelex.flightplanner.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateTimeConverter {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static LocalDateTime dateTimeFromString(String date) {
        Pattern pattern = Pattern.compile("(\\d{4})-(\\d{2})-(\\d{2}) (\\d{2}):(\\d{2})");
        Matcher matcher = pattern.matcher(date);

        if (matcher.matches()) {
            return LocalDateTime.parse(date, dateTimeFormatter);
        } else {
            Pattern datePattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
            Matcher dateMatcher = datePattern.matcher(date);
            if (dateMatcher.matches()) {
                return LocalDate.parse(date, DATE_FORMATTER).atStartOfDay();
            } else {
                throw new IllegalArgumentException("Invalid date format: " + date);
            }
        }
    }
}
