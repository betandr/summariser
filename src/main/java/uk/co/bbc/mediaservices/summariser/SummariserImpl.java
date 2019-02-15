package uk.co.bbc.mediaservices.summariser;

/**
 * SummariserImpl is an implementation of the Summariser interface which runs
 * a summarise task, given a job.
 */
public class SummariserImpl implements Summariser {
    public void summarise(Job job) {
        System.out.println("Recieved job:");
        System.out.println(job.getCategoryMappingsFilename());
        System.out.println(job.getViewingsFilename());
        System.out.println(job.getOutputFilename());
    }
}
