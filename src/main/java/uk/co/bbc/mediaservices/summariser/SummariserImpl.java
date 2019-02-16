package uk.co.bbc.mediaservices.summariser;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * SummariserImpl is an implementation of the Summariser interface which runs
 * a summarise task, given a job.
 */
public class SummariserImpl implements Summariser {

    protected void process(String line) {
        System.out.println(line);
    }

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
}
