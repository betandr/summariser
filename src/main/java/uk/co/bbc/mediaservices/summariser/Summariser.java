package uk.co.bbc.mediaservices.summariser;

/**
 * Summariser represents the public interface for all implementations.
 */
public interface Summariser {
    /**
     * Run the summarise task with the supplied job.
     * @params job Containing the information required to run a summarise task
     */
    public void summarise(Job job);
}
