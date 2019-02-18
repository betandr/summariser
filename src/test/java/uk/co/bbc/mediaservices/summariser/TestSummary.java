package uk.co.bbc.mediaservices.summariser;

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
        assertEquals(smry.getMonth(), "January");
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
            "\nMonth of Year: January" +
            "\nCategory: CATEGORY" +
            "\nWatch Time (secs): 1234]";

        assertEquals(smry.toString(), s);
    }
}
