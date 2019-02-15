package uk.co.bbc.mediaservices.summariser;

import uk.co.bbc.mediaservices.summariser.Summariser;

/**
 * MockSummariser is a mock object which is used to inject into the
 * CommandLineHandler and, after execution, retrieve the Job that has been
 * created from the command line arguments.
 */
public class MockSummariser implements Summariser {

    private Job job;

    public Job getJob() {
        return job;
    }

    public void summarise(Job job) {
        this.job = job;
    }
}
