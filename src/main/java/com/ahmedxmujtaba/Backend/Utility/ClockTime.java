package com.ahmedxmujtaba.Backend.Utility;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ClockTime {

    // Method to format the current time as a 12-hour string without seconds (hh:mm a)
    public static String getCurrentTime12HourWithoutSeconds() {
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        return currentTime.format(formatter);
    }

    // Method to get the current time as a LocalTime object
    public static LocalTime getCurrentLocalTime() {
        return LocalTime.now();
    }

    // Method to parse a LocalTime object into a string with 12-hour format (hh:mm a)
    public static String parseLocalTimeTo12HourString(LocalTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        return time.format(formatter);
    }

    // Method to parse a string with 12-hour format (hh:mm a) into a LocalTime object
    public static LocalTime parseStringToLocalTime12Hour(String time12Hour) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
            return LocalTime.parse(time12Hour, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid time format. Please provide time in hh:mm a format.");
            return null;
        }
    }

    // Method to format a string time in 24-hour format (HH:mm) to 12-hour format (hh:mm a) without seconds
    public static String formatTimeWithoutSecondsTo12Hour(String time24Hour) {
        try {
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("HH:mm");
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("hh:mm a");
            LocalTime time = LocalTime.parse(time24Hour, inputFormatter);
            return time.format(outputFormatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid time format. Please provide time in HH:mm format.");
            return null;
        }
    }
}
