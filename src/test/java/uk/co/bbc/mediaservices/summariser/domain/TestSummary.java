package uk.co.bbc.mediaservices.summariser.domain;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;


/**
 * TestSummary tests the functionality of the Summary bean.
 */
public class TestSummary {

    @Test
    public void testSummaryReturnsCorrectWeekStart() {
        LocalDateTime dateTime = LocalDateTime.ofEpochSecond(
            1546300800,
            0,
            ZoneOffset.UTC
        );

        Summary summary = new Summary(-1, dateTime, null, -1);
        assertEquals("January", summary.getMonth());
    }

    @Test
    public void testSummaryReturnsCorrectWeekNumber() {
        LocalDateTime startOfYearDateTime = LocalDateTime.ofEpochSecond(
            1546300800,
            0,
            ZoneOffset.UTC
        );

        LocalDateTime duringYearDateTime = LocalDateTime.ofEpochSecond(
            1550488036,
            0,
            ZoneOffset.UTC
        );

        LocalDateTime endOfYearDateTime = LocalDateTime.ofEpochSecond(
            1577836799,
            0,
            ZoneOffset.UTC
        );

        Summary startOfYearSummary = new Summary(-1, startOfYearDateTime, null, -1);
        Summary duringYearSummary = new Summary(-1, duringYearDateTime, null, -1);
        Summary endOfYearSummary = new Summary(-1, endOfYearDateTime, null, -1);

        assertEquals("January", startOfYearSummary.getMonth());
        assertEquals(1, startOfYearSummary.getWeekNumber());

        assertEquals("December", endOfYearSummary.getMonth());
        // week is still 1 as it's the end of the year
        assertEquals(1, endOfYearSummary.getWeekNumber());

        assertEquals("February", duringYearSummary.getMonth());
        assertEquals(8, duringYearSummary.getWeekNumber());
    }

    @Test
    public void testBeanCreatedCorrectly() {
        LocalDateTime dateTime = LocalDateTime.ofEpochSecond(
            1550484440,
            0,
            ZoneOffset.UTC
        );
        int userIdentifier = 1234567890;
        String category = "CATEGORY";
        int watchTimeInSeconds = 1234;
        Summary smry = new Summary(userIdentifier, dateTime, category, watchTimeInSeconds);

        assertEquals(smry.getUserIdentifier(), userIdentifier);
        assertEquals(smry.getMonth(), "February");
        assertEquals(smry.getCategory(), category);
        assertEquals(smry.getWatchTimeInSeconds(), watchTimeInSeconds);
    }

    @Test
    public void testToString() {
        LocalDateTime dateTime = LocalDateTime.ofEpochSecond(
            1550484440,
            0,
            ZoneOffset.UTC
        );
        int userIdentifier = 1234567890;
        String category = "CATEGORY";
        int watchTimeInSeconds = 1234;
        Summary smry = new Summary(userIdentifier, dateTime, category, watchTimeInSeconds);

        String s = "[Summary\nUser Identifier: 1234567890" +
            "\nMonth of Year: February" +
            "\nCategory: CATEGORY" +
            "\nWatch Time (secs): 1234]";

        assertEquals(smry.toString(), s);
    }
}
