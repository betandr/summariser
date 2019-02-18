package uk.co.bbc.mediaservices.summariser;

import java.time.format.TextStyle;
import java.time.temporal.WeekFields;
import java.util.Locale;
import java.util.Calendar;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an idempotent single Summary.
 * Java beans are used to simplify the interface of intermediary code allowing
 * fields to be added or updated without requiring too much code change.
 */
public class Summary {
    private int userIdentifier;
    private LocalDateTime dateTime;
    private String category;
    private int watchTimeInSeconds;

    public Summary(
        int userIdentifier,
        LocalDateTime dateTime,
        String category,
        int watchTimeInSeconds) {
            this.userIdentifier = userIdentifier;
            this.dateTime = dateTime;
            this.category = category;
            this.watchTimeInSeconds = watchTimeInSeconds;
    }

    public int getUserIdentifier() {
        return this.userIdentifier;
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    public String getMonth() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
            "MMMM",
            Locale.ENGLISH
        );
        return dateTime.format(formatter);
    }

    public int getWeekNumber() {
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        return this.dateTime.get(weekFields.weekOfWeekBasedYear());
    }

    public String getCategory() {
        return this.category;
    }

    public int getWatchTimeInSeconds() {
        return this.watchTimeInSeconds;
    }

    public String toString() {
        return "[Summary\nUser Identifier: " + this.userIdentifier +
            "\nMonth of Year: " + this.getMonth() +
            "\nCategory: " + this.category +
            "\nWatch Time (secs): " + this.watchTimeInSeconds + "]";
    }
}
