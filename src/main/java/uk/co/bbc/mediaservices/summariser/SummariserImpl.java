package uk.co.bbc.mediaservices.summariser;

import java.io.FileInputStream;
import java.io.Reader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Iterator;
import java.time.LocalDate;
import java.time.Instant;
import java.time.ZoneId;
import javax.json.JsonArray;
import javax.json.JsonReader;
import javax.json.JsonString;
import javax.json.Json;
import javax.json.JsonObject;

/**
 * SummariserImpl is an implementation of the Summariser interface which runs
 * a summarise task, given a job.
 */
public class SummariserImpl implements Summariser {

    private Map<String,String> mappings;

    /**
     * Safe lookup of category mappings
     */
    protected String categoryMapping(String programmeName) {
        String category = "Unknown";

        if (mappings != null && mappings.containsKey(programmeName)) {
            category = mappings.get(programmeName);
        }

        return category;
    }

    /**
     * Translates a viewing into an output Summary format.
     * @param viewing The viewing bean to translate.
     * @param Summary A summary bean used for rendering output
     */
    protected Summary viewingToSummary(Viewing viewing) {
        LocalDate date = Instant.ofEpochMilli(
                viewing.getDateInEpochSeconds()
            ).atZone(ZoneId.systemDefault()).toLocalDate();

        return new Summary(
            viewing.getUserIdentifier(),
            date.getMonth(),
            categoryMapping(viewing.getProgrammeName()));
    }

    /**
     * Processes a single line of the viewings input.
     */
    protected void process(String line) {
        try {
            Viewing v = stringToViewing(line);
            System.out.println(viewingToSummary(v));
        } catch (Exception e) {
            System.err.println(
                "ERROR: could not process " + line +
                ": " + e.getMessage());
        }
    }

    /**
     * Extracts values from a single viewings comma-delimited string.
     * @param line A comma-delimited string of the format `int,int,str,int,str`.
     * @return Viewing an object containing safe versions of the line
     */
    protected Viewing stringToViewing(String line) throws Exception {
        String[] lineData = line.split(",", -1);
        Viewing viewing = null;

        if (lineData.length == 5) {
            viewing = new Viewing(
                Integer.parseInt(lineData[0]),
                Integer.parseInt(lineData[1]),
                lineData[2],
                Integer.parseInt(lineData[3]),
                lineData[4]);
        } else {
            throw new Exception("not expected format");
        }

        return viewing;
    }

    /**
     * Set the mappings
     * @param mappings The Map<String,String> key/value mappings.
     */
    protected void setMappings(Map<String,String> mappings) {
        this.mappings = mappings;
    }

    /**
     * Converts an InputStream containing JSON to a Map<String,String> containing
     * key/value pairs mapping programmes to categories for lookup during summarise
     * @param is The InputStream containing JSON.
     * @return Map<String,String> mappings of programmes to categories
     */
    protected Map<String,String> jsonToMappings(Reader r) {
        Map<String,String> ms = new HashMap<String,String>();
        try (JsonReader rdr = Json.createReader(r)) {
            JsonObject obj = rdr.readObject();

            Iterator<String> itr = obj.keySet().iterator();
            while(itr.hasNext()){
                String key = itr.next();
                JsonArray programmes = obj.getJsonArray(key);
                for (JsonString programme : programmes.getValuesAs(JsonString.class)) {
                    ms.put(programme.getString(), key);
                }
            }
        }
        return ms;
    }

    /**
     * Scans each line to process it.
     * @param sc A java.util.Scanner used to parse strings.
     */
    protected void scan(Scanner sc) {
        try {
            while (sc.hasNextLine()) {
                process(sc.nextLine());
            }
            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
    }

    /**
     * Loads the JSON content of the files.getCategoryMappingsFilename()
     * and parses this into a Map, containing a key/value pair mapping the programme
     * name to the category.
     * Mapping is done this way for access speed from a map.
     * NB: This MVP does *not* support multiple categories.
     */
    public void loadMappings(SummariserFiles files) throws FileNotFoundException {
        Map<String,String> ms = jsonToMappings(new FileReader(files.getCategoryMappingsFilename()));
        setMappings(ms);
    }

    /**
     * The entry point to the Summariser class, triggering the summerise work.
     * @param files The object containing the files that summarise will run with.
     */
    public void summarise(SummariserFiles files) {
        FileInputStream fis = null;
        Scanner sc = null;
        try {
            fis = new FileInputStream(files.getViewingsFilename());
            sc = new Scanner(fis, "UTF-8");
            scan(sc);
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException ioe) {
                    System.err.println(ioe.getMessage());
                }
            }
        }
    }
}
