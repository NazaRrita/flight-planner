package io.codelex.flightplanner.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateTimeConverter {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static LocalDateTime localDateTimeFromString(String dateTime) {
        return LocalDateTime.parse(dateTime, DATE_TIME_FORMATTER);
    }

    public static LocalDate localDateFromString(String date) {
        return LocalDate.parse(date, DATE_FORMATTER);
    }

    public static boolean isDateValid(String date) {
        Pattern pattern = Pattern.compile("(\\d{4})-(\\d{2})-(\\d{2}) (\\d{2}):(\\d{2})");
        Matcher matcher = pattern.matcher(date);
        return matcher.matches();
    }

    public static boolean isDateWithoutTime(String date) {
        Pattern pattern = Pattern.compile("(\\d{4})-(\\d{2})-(\\d{2})");
        Matcher matcher = pattern.matcher(date);
        return matcher.matches();
    }

    public static LocalDateTime dateValidationAndConvertToLocalDateTime(String date) {
        if (isDateValid(date)) {
            return localDateTimeFromString(date);
        } else if (isDateWithoutTime(date)) {
            return localDateFromString(date).atStartOfDay();
        } else {
            throw new IllegalArgumentException("Invalid date format: " + date);
        }
    }
}
