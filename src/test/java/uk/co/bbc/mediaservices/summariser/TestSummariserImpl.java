package uk.co.bbc.mediaservices.summariser;

import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.ByteArrayInputStream;

// import static org.mockito.Mockito.spy;
// import static org.mockito.Mockito.times;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Matchers.any;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * TestSummariserImpl tests the functionality of the Summariser class.
 */
public class TestSummariserImpl {

    @Test
    public void testSummaryDurationsHasSingleKeys() {
        SummariserImpl summariser = new SummariserImpl();
        Map<String,MutableInt> durations = summariser.summaryDurations();

        Viewing viewing = new Viewing(1540641600, 98765432, "News", 3600, "mobile");
        Summary summary = summariser.viewingToSummary(viewing);

        String key =
            summary.getUserIdentifier() + "_" +
            summary.getWeekNumber() + "_" +
            summary.getCategory();

        MutableInt mi = new MutableInt();
        mi.add(1);

        durations.put(key, mi);
        assertTrue(durations.size() == 1);

        String key2 =
            summary.getUserIdentifier() + "_" +
            summary.getWeekNumber() + "_" +
            summary.getCategory();

        durations.put(key2, mi);
        assertTrue(durations.size() == 1);
    }

    @Test
    public void testSummaryDurationsReturnsAMap() {
        SummariserImpl summariser = new SummariserImpl();
        Object durations = summariser.summaryDurations();
        assertTrue(durations != null);
        assertTrue(durations instanceof Map);
    }

    @Test
    public void testEpochToDate() {
        SummariserImpl summariser = new SummariserImpl();
        Viewing viewing = new Viewing(1540641600, 0, "-", 0, "-");
        Summary s = summariser.viewingToSummary(viewing);
        assertEquals(s.getMonth(), "October");
    }

    @Test
    public void testViewingToSummary() {
        SummariserImpl summariser = new SummariserImpl();
        Viewing viewing = new Viewing(1540641600, 98765432, "News", 3600, "mobile");
        Summary s = summariser.viewingToSummary(viewing);

        assertEquals(s.getUserIdentifier(), 98765432);
        assertEquals(s.getMonth(), "October");
        // category is Unknown as we've not set any mappings
        assertEquals(s.getCategory(), "Unknown");
    }

    @Test
    public void testStringToViewing() {
        SummariserImpl s = new SummariserImpl();
        Viewing v = null;
        try {
             v = s.stringToViewing("1540641600,98765432,News,3600,mobile");
        } catch (Exception e) {
            // ignore
        }

        assertEquals(v.getDateInEpochSeconds(), 1540641600);
        assertEquals(v.getUserIdentifier(), 98765432);
        assertEquals(v.getProgrammeName(), "News");
        assertEquals(v.getWatchTimeInSeconds(), 3600);
        assertEquals(v.getDeviceType(), "mobile");
    }

    @Test
    public void testScanTwoLines() {
        Scanner sc = new Scanner(
            "1538688600,12345678,News,3600,ip_tv\n" +
            "1540641600,98765432,News,3600,mobile"
        );

        MockSummariserImpl s = new MockSummariserImpl();
        s.scan(sc);
        assertEquals(s.getCount(), 2);
    }

    @Test
    public void testScanSingleLineGetsCorrectString() {
        String str = "1538688600,12345678,News,3600,ip_tv";
        Scanner sc = new Scanner(str + "\n");

        MockSummariserImpl s = new MockSummariserImpl();
        s.scan(sc);
        assertEquals(s.getCount(), 1);
    }

    @Test
    public void testJsonToMappings() {
        String jsonStr = "{\"foo\": [\"one\", \"two\"],\"bar\": [\"three\", \"four\"]}";
        Reader rdr = new StringReader(jsonStr);

        SummariserImpl s = new SummariserImpl();
        Map mappings = s.jsonToMappings(rdr);
        assertEquals(mappings.get("one"), "foo");
        assertEquals(mappings.get("two"), "foo");
        assertEquals(mappings.get("three"), "bar");
        assertEquals(mappings.get("four"), "bar");
    }
}
