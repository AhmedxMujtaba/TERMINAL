package com.ahmedxmujtaba.Backend.Utility;

import java.io.Serializable;

public class DurationTime implements Serializable {
    private int hours;
    private int minutes;

    public DurationTime(int hours, int minutes) {
        this.hours = hours;
        this.minutes = minutes;
        normalizeTime(); // Ensure valid time format
    }

    public DurationTime(String timeString) {
        parseTime(timeString);
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    private void parseTime(String timeString) {
        String[] parts = timeString.split("\\s+");
        for (String part : parts) {
            if (part.endsWith("h")) {
                this.hours = Integer.parseInt(part.substring(0, part.length() - 1));
            } else if (part.endsWith("m")) {
                this.minutes = Integer.parseInt(part.substring(0, part.length() - 1));
            }
        }
        normalizeTime();
    }

    private void normalizeTime() {
        if (minutes >= 60) {
            hours += minutes / 60;
            minutes %= 60;
        } else if (minutes < 0) {
            int negativeMinutes = Math.abs(minutes);
            hours -= (negativeMinutes / 60) + 1;
            minutes = 60 - (negativeMinutes % 60);
        }
        if (hours < 0) {
            hours = 0;
            minutes = 0;
        }
    }

    @Override
    public String toString() {
        return String.format("%dh %dm", hours, minutes);
    }

    public static DurationTime valueOf(String timeString) {
        return new DurationTime(timeString);
    }
}
