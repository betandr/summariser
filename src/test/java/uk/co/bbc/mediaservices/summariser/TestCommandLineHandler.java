package uk.co.bbc.mediaservices.summariser;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import java.util.Scanner;

import uk.co.bbc.mediaservices.summariser.domain.SummariserFiles;

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
    public void testExtractValidArgs() throws Exception {
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

        SummariserFiles files = handler.extractFilenames(args);
        assertEquals(files.getCategoryMappingsFilename(), categoryMappingsFilename);
        assertEquals(files.getViewingsFilename(), viewingsFilename);
        assertEquals(files.getOutputFilename(), outputFilename);
    }


    @Test
    public void testHandlerCallsSummarise() {
        CommandLineHandler handler = new CommandLineHandler();
        MockSummariser summariser = new MockSummariser();
        handler.setSummariser(summariser);

        String args[] = {
            "--category-mappings=/foo/category_mappings.json",
            "--viewings=/bar/viewings.csv",
            "--output=/baz/output.json"
        };

        handler.handle(args);
        assertEquals(summariser.getCount(), 1);

        // run again to ensure side-effect
        handler.handle(args);
        assertEquals(summariser.getCount(), 2);
    }
}
