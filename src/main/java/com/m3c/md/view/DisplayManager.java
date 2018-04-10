package com.m3c.md.view;

import com.m3c.md.model.BookManagerParallel;
import com.m3c.md.model.Constants;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * DisplayManager
 *
 * @author Metin Dagcilar
 * @version 1.0
 * @since 2018-04-03
 */

public class DisplayManager {

    private static org.apache.log4j.Logger logger = Logger.getLogger(BookManagerParallel.class);
    private PrintStream printStream;

    /**
     * Prints the top 3 occurring words in a file.
     *
     * @param topThreeWordsList - List of Map entries containing words and their counts
     * @param populateTime      - time taken to populate the HashMap
     * @param filterTime        - time taken to filter the HashMap
     * @param outResultsToFile  - flag, whether to print output to a file
     */
    public void printTopThreeWords(List<Map.Entry<String, Integer>> topThreeWordsList,
                                   long totalTime, long populateTime, long filterTime, boolean outResultsToFile) {

        if (outResultsToFile) changeOutputStreamToFile();

        System.out.println("" +
                "Total time taken: (" + totalTime + "ms), " +
                "populate the HashMap (" + populateTime + "ms), " +
                "get the top 3 words (" + filterTime + "ms)");
        System.out.println("The top 3 words: ");

        for (Map.Entry entry : topThreeWordsList) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
        System.out.println();
    }


    /**
     * Changes the PrintStream to a file, to allow ease saving of results
     */
    private void changeOutputStreamToFile() {
        if (printStream != null) {
            return;
        }

        String fileOutput = Constants.OUTPUT_RESULTS_FILE;
        try {
            printStream = new PrintStream(new FileOutputStream(fileOutput), true);
            System.setOut(printStream);
        } catch (FileNotFoundException e) {
            logger.error(fileOutput + " not found " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void displayExceptionMessage(String message) {
        System.out.println(message);
    }

    /**
     * Prints to standard output the contents of a file
     *
     * @param bufferedReader A BufferedReader
     */
    public static void printContentsOfFile(BufferedReader bufferedReader) {
        if (bufferedReader == null) return;

        try {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                logger.error("IO Exception " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
