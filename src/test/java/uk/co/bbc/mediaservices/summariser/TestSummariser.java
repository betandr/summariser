package uk.co.bbc.mediaservices.summariser;

import java.util.Scanner;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Matchers.any;

import org.junit.Test;


/**
 * TestSummariser tests the functionality of the Summariser class.
 */
public class TestSummariser {
    @Test
    public void testScanTwoLines() {
        Scanner sc = new Scanner(
            "1538688600,12345678,News,3600,ip_tv\n" +
            "1540641600,98765432,News,3600,mobile"
        );

        SummariserImpl s = spy(new SummariserImpl());
        s.scan(sc);
        verify(s, times(2)).process(any());
    }

    @Test
    public void testScanSingleLineGetsCorrectString() {
        String str = "1538688600,12345678,News,3600,ip_tv";
        Scanner sc = new Scanner(str + "\n");

        SummariserImpl s = spy(new SummariserImpl());
        s.scan(sc);
        verify(s, times(1)).process(str);
    }
}
