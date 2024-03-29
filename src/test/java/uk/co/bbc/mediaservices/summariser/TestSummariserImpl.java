package uk.co.bbc.mediaservices.summariser;

import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

// import static org.mockito.Mockito.spy;
// import static org.mockito.Mockito.times;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Matchers.any;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import uk.co.bbc.mediaservices.summariser.domain.Duration;
import uk.co.bbc.mediaservices.summariser.domain.Viewing;
import uk.co.bbc.mediaservices.summariser.domain.Summary;


/**
 * TestSummariserImpl tests the functionality of the Summariser class.
 */
public class TestSummariserImpl {
    private final ByteArrayOutputStream stdout = new ByteArrayOutputStream();
    private final PrintStream prevOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(stdout));
    }

    @After
    public void restoreStreams() {
        System.setOut(prevOut);
    }

    @Test
    public void testDurationsOverFifteenHoursTriggersStdoutMessage() {
        SummariserImpl summariser = new SummariserImpl();
        Map<String, Duration> durations = summariser.durations();
        int halfDuration = (SummariserImpl.FIFTEEN_HOURS / 2) + 1;

        Viewing viewing = new Viewing(1540641600, 98765432, "News", halfDuration, "mobile");
        Summary summary = summariser.viewingToSummary(viewing);
        summariser.addSummary(durations, summary);
        Viewing viewing2 = new Viewing(1540641601, 98765432, "News", halfDuration, "mobile");
        Summary summary2 = summariser.viewingToSummary(viewing2);
        summariser.addSummary(durations, summary2);

        String expectedError =
            "WARNING: 98765432 consumed 15 hours of bbc " +
            "content between 22/10/18 and 29/10/18\n";

        assertEquals(expectedError, stdout.toString());
    }

    @Test
    public void testSummaryDurationsReturnsAMap() {
        SummariserImpl summariser = new SummariserImpl();
        Object durations = summariser.durations();
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
