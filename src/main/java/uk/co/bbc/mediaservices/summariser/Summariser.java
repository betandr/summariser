package uk.co.bbc.mediaservices.summariser;

import java.util.Scanner;
import java.io.FileNotFoundException;

/**
 * Summariser represents the public interface for all implementations.
 */
public interface Summariser {

    /**
     * Load the category mappings
     */
    public void loadMappings(SummariserFiles files) throws FileNotFoundException;

    /**
     * Run the summarise task with the supplied SummariserFiles.
     * @params files Containing the files required to run a summarise task
     */
    public void summarise(SummariserFiles files);
}
