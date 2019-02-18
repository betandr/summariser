package uk.co.bbc.mediaservices.summariser;

import java.time.Month;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;


/**
 * TestSummary tests the functionality of the Summary bean.
 */
public class TestSummary {

    @Test
    public void testBeanCreatedCorrectly() {
        int userIdentifier = 1234567890;
        Month monthOfYear = Month.OCTOBER;
        String category = "CATEGORY";
        Summary smry = new Summary(userIdentifier, monthOfYear, category);

        assertTrue(smry.getUserIdentifier() == userIdentifier);
        assertEquals(smry.getMonthOfYear(), monthOfYear);
        assertEquals(smry.getCategory(), category);
    }

    @Test
    public void testToString() {
        int userIdentifier = 1234567890;
        Month monthOfYear = Month.OCTOBER;
        String category = "CATEGORY";
        Summary smry = new Summary(userIdentifier, monthOfYear, category);

        String s = "Summary: \nUser Identifier: " + userIdentifier +
            "\nMonth of Year: October" +
            "\nCategory: CATEGORY";

        assertEquals(smry.toString(), s);
    }
}
