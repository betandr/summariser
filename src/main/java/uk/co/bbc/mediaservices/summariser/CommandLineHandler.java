package uk.co.bbc.mediaservices.summariser;

import java.io.File;

/**
 * CommandLineHandler translates command line arguments to a Job which can be
 * passed to the Summariser to execute. The Summariser is injected as an
 * implementation of the Strategy GoF pattern.
 *
 * This could be handled with Commons CLI
 * (http://commons.apache.org/proper/commons-cli/) if a more robust
 * implementation was required.
 */
public class CommandLineHandler {

    private Summariser summariser;

    public void setSummariser(Summariser summariser) {
        this.summariser = summariser;
    }

    /**
     * handle accepts a String array and extracts the values of
     * `category-mappings`, `viewings`, and `output` filenames.
     * @param args A String array containing command line arguments.
     */
    public void handle(String args[]) throws UnsupportedOperationException {
        if (summariser == null) {
            throw new UnsupportedOperationException("no summariser found to perform work");
        }

        String mappingsFilename = null;
        String viewingsFilename = null;
        String outputFilename = null;

        for (int i = 0; i < args.length; i++) {
            String s = args[i];
            if (s != null && s.contains("category-mappings")) {
                mappingsFilename = s.substring(s.indexOf("=") + 1, s.length());
            }

            if (s != null && s.contains("viewings")) {
                viewingsFilename = s.substring(s.indexOf("=") + 1, s.length());
            }

            if (s != null && s.contains("output")) {
                outputFilename = s.substring(s.indexOf("=") + 1, s.length());
            }
        }

        Job job = new Job(mappingsFilename, viewingsFilename, outputFilename);
        summariser.summarise(job);
    }
}
