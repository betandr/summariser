package uk.co.bbc.mediaservices.summariser;

import java.util.Scanner;
import java.util.Map;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.ByteArrayInputStream;

// import static org.mockito.Mockito.spy;
// import static org.mockito.Mockito.times;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Matchers.any;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;


/**
 * TestSummariserImpl tests the functionality of the Summariser class.
 */
public class TestSummariserImpl {

    @Test
    public void testStringToViewing() {
        SummariserImpl s = new SummariserImpl();
        Viewing v = null;
        try {
             v = s.stringToViewing("1540641600, 98765432, News, 3600, mobile");
        } catch (Exception e) {
            // ignore
        }

        assertTrue(v.getDateInEpochSeconds() == 1540641600);
        assertTrue(v.getUserIdentifier() == 98765432);
        assertEquals(v.getProgrammeName(), "News");
        assertTrue(v.getWatchTimeInSeconds() == 3600);
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
