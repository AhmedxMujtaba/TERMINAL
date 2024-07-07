package com.ahmedxmujtaba.Backend.Utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class Date {

    // Define the date format as a constant
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Validates if the input date string is in the format dd/MM/yyyy.
     *
     * @param inputDate the input date string
     * @return true if the input date is valid, false otherwise
     */
    public static boolean isValidDate(String inputDate) {
        try {
            LocalDate.parse(inputDate, FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Parses the input date string to a LocalDate object.
     *
     * @param inputDate the input date string
     * @return the parsed LocalDate object
     * @throws DateTimeParseException if the input date is invalid
     */
    public static LocalDate parseDate(String inputDate) throws DateTimeParseException {
        return LocalDate.parse(inputDate, FORMATTER);
    }

    /**
     * Formats a LocalDate object to a string in the format dd/MM/yyyy.
     *
     * @param date the LocalDate object
     * @return the formatted date string
     */
    public static String formatDate(LocalDate date) {
        return date.format(FORMATTER);
    }
    public static LocalDate currentDate(){
        return parseDate(formatDate(LocalDate.now()));
    }
    public static double ageCalculation(String dob) {
        LocalDate currentDate = currentDate();
        LocalDate dateOfBirth;
        try {
             dateOfBirth = parseDate(dob);
        } catch (DateTimeParseException e) {
            throw new RuntimeException(e);
        }
        if (dateOfBirth != null && currentDate != null) {
            long daysBetween = ChronoUnit.DAYS.between(dateOfBirth, currentDate);
            return daysBetween / 365.25; // 365.25 accounts for leap years
        } else {
            return 0.0;
        }
    }
}

