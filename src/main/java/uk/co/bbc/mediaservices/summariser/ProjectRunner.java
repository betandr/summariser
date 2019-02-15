package uk.co.bbc.mediaservices.summariser;

/**
 * The entry point to the Summariser application.
 */
public class ProjectRunner {

    /**
     * Main entry point for the `ProjectRunner` and accepts three arguments
     * in `args` array:
     * --category-mappings=/path/to/file
     * --viewings=/path/to/file
     * --output=/path/to/file
     * @param args A String array containing command line arguments
     */
    public static void main(String args[]) {
        CommandLineHandler handler = new CommandLineHandler();
        handler.setSummariser(new SummariserImpl()); // inject Summariser
        try {
            handler.handle(args);
        } catch (UnsupportedOperationException uoe) {
            System.err.println("error: " + uoe.getMessage());
        }
    }
}
