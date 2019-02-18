package uk.co.bbc.mediaservices.summariser;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * Represents an idempotent single Summary.
 * Java beans are used to simplify the interface of intermediary code allowing
 * fields to be added or updated without requiring too much code change.
 */
public class Summary {
    private int userIdentifier;
    private Month monthOfYear;
    private String category;

    public Summary(int userIdentifier, Month monthOfYear, String category) {
        this.userIdentifier = userIdentifier;
        this.monthOfYear = monthOfYear;
        this.category = category;
    }

    public int getUserIdentifier() {
        return this.userIdentifier;
    }

    public Month getMonthOfYear() {
        return this.monthOfYear;
    }

    public String getMonthString() {
        return monthOfYear.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
    }

    public String getCategory() {
        return this.category;
    }

    public String toString() {
        return "Summary: \nUser Identifier: " + this.userIdentifier +
            "\nMonth of Year: " + this.getMonthString() + 
            "\nCategory: " + this.category;
    }
}
