package uk.co.bbc.mediaservices.summariser.domain;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;


/**
 * TestViewing tests the functionality of the Viewing bean.
 */
public class TestViewing {

    @Test
    public void testBeanCreatedCorrectly() {
        int dateInEpochSeconds = 1234567890;
        int userIdentifier = 12345678;
        String programmeName = "FOO BAR BAZ";
        int watchTimeInSeconds = 1111;
        String deviceType = "TEST";

        Viewing v = new Viewing(
            dateInEpochSeconds,
            userIdentifier,
            programmeName,
            watchTimeInSeconds,
            deviceType
        );

        assertTrue(v.getDateInEpochSeconds() == dateInEpochSeconds);
        assertTrue(v.getUserIdentifier() == userIdentifier);
        assertEquals(v.getProgrammeName(), programmeName);
        assertTrue(v.getWatchTimeInSeconds() == watchTimeInSeconds);
        assertEquals(v.getDeviceType(), deviceType);
    }

    @Test
    public void testToString() {
        int dateInEpochSeconds = 1234567890;
        int userIdentifier = 12345678;
        String programmeName = "FOO BAR BAZ";
        int watchTimeInSeconds = 1111;
        String deviceType = "TEST";

        String s =  "Date in Epoch Seconds: " + dateInEpochSeconds +
            "\nUser Identifier: " + userIdentifier +
            "\nProgramme Name: " + programmeName +
            "\nWatch Time In Seconds: " + watchTimeInSeconds +
            "\nDevice Type: " + deviceType + "\n";

        Viewing v = new Viewing(
            dateInEpochSeconds,
            userIdentifier,
            programmeName,
            watchTimeInSeconds,
            deviceType
        );
        assertEquals(v.toString(), s);
    }
}
