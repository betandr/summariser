package uk.co.bbc.mediaservices.summariser;

import uk.co.bbc.mediaservices.summariser.Summariser;

public class MockSummariser implements Summariser {

    private Job job;

    public Job getJob() {
        return job;
    }

    public void summarise(Job job) {
        this.job = job;
    }
}
