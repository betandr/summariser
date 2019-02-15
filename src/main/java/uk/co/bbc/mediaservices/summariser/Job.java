package uk.co.bbc.mediaservices.summariser;

/**
 * Job encapsulates the information that the Summariser needs to complete a
 * summarise task.
 */
public class Job {

    private String categoryMappingsFilename;
    private String viewingsFilename;
    private String outputFilename;

    public Job(
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
