package uk.co.bbc.mediaservices.summariser;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import uk.co.bbc.mediaservices.summariser.MockSummariser;

/**
 * TestCommandLineHandler tests the functionality of the CommandLineHandler
 * class.
 */
public class TestCommandLineHandler {

    @Test(expected = UnsupportedOperationException.class)
    public void testSummariserNotInjectedThrowsException() throws Exception {
        CommandLineHandler handler = new CommandLineHandler();
        handler.handle(new String[0]);
    }

    @Test
    public void testHandleValidArgs() throws Exception {
        CommandLineHandler handler = new CommandLineHandler();
        MockSummariser summariser = new MockSummariser();
        handler.setSummariser(summariser);

        String categoryMappingsFilename = "/foo/category_mappings.json";
        String viewingsFilename = "/bar/viewings.csv";
        String outputFilename = "/baz/output.json";

        String args[] = {
            "--category-mappings=" + categoryMappingsFilename,
            "--viewings=" + viewingsFilename,
            "--output=" + outputFilename
        };

        // Handle the command line args
        handler.handle(args);

        // The 'job' contains data that the Summariser needs to run
        Job job = summariser.getJob();

        assertEquals(job.getCategoryMappingsFilename(), categoryMappingsFilename);
        assertEquals(job.getViewingsFilename(), viewingsFilename);
        assertEquals(job.getOutputFilename(), outputFilename);
    }
}
