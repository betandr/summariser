package uk.co.bbc.mediaservices.summariser;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;


/**
 * TestDuration tests the functionality of the Duration.
 */
public class TestDuration {

    @Test
    public void testNewCategoryReturnsZero() {
        Duration duration = new Duration();
        assertEquals(0, duration.getCategoryDuration("foo"));
    }

    @Test
    public void testTotalDurationForNewDurationIsZero() {
        Duration duration = new Duration();
        assertEquals(0, duration.getTotalDuration());
    }

    @Test
    public void testAddingDurationSetsCorrectTotalDuration() {
        Duration duration = new Duration();
        assertEquals(0, duration.getTotalDuration());
    }

    @Test
    public void testOverallIncreases() {
        Duration duration = new Duration();
        assertEquals(0, duration.getTotalDuration());
        duration.addCategoryDuration("foo", 2);
        duration.addCategoryDuration("bar", 2);
        assertEquals(4, duration.getTotalDuration());
    }

    @Test
    public void testSingleCategory() {
        Duration duration = new Duration();
        duration.addCategoryDuration("foo", 7);
        assertEquals(7, duration.getCategoryDuration("foo"));
        duration.addCategoryDuration("foo", 3);
        assertEquals(10, duration.getCategoryDuration("foo"));
    }

    @Test
    public void testEnsureSingleCategoriesDoNotAffectEachOther() {
        Duration duration = new Duration();
        duration.addCategoryDuration("foo", 1);
        duration.addCategoryDuration("bar", 2);
        assertEquals(1, duration.getCategoryDuration("foo"));
        assertEquals(2, duration.getCategoryDuration("bar"));
    }
}
