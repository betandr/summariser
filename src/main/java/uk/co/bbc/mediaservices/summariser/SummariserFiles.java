package uk.co.bbc.mediaservices.summariser;

/**
 * SummariserFiles encapsulates the information that the Summariser needs to complete a
 * summarise task.
 */
public class SummariserFiles {

    private String categoryMappingsFilename;
    private String viewingsFilename;
    private String outputFilename;

    public SummariserFiles(
        String categoryMappingsFilename,
        String viewingsFilename,
        String outputFilename) {
            this.categoryMappingsFilename = categoryMappingsFilename;
            this.viewingsFilename = viewingsFilename;
            this.outputFilename = outputFilename;
        }

    public String getCategoryMappingsFilename() {
        return this.categoryMappingsFilename;
    }

    public String getViewingsFilename() {
        return this.viewingsFilename;
    }

    public String getOutputFilename() {
        return this.outputFilename;
    }
}
