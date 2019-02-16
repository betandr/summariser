package uk.co.bbc.mediaservices.summariser;

import java.util.Scanner;

/**
 * Summariser represents the public interface for all implementations.
 */
public class MockSummariser implements Summariser {
    private int count;
    /**
     * Run the summarise task with the supplied SummariserFiles.
     * @params files Containing the files required to run a summarise task
     */
    public void summarise(SummariserFiles files) {
        this.count++;
    }

    // Count is incremented each time `summarise(SummariserFiles files)` is
    // called and used as a probe that CommandLineHandler is correctly calling
    // summarise when handle is executed.
    protected int getCount() {
        return this.count;
    }
}
