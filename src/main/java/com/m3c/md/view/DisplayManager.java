package com.m3c.md.view;

import java.io.*;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Collections.reverseOrder;

/**
 * DisplayManager
 *
 * @author Metin Dagcilar
 * @version 1.0
 * @since 2018-04-03
 */

public class DisplayManager {

    private PrintStream printStream;

    /**
     * Prints the top 3 occurring words in a file.
     *
     * @param hashMap          - the HashMap containing words and their counts
     * @param populateTime     - time taken to populate the HashMap
     * @param outResultsToFile - flag, whether to print output to a file
     */
    public void printTopThreeWords(Map<String, Integer> hashMap, long populateTime, boolean outResultsToFile) {
        if (outResultsToFile) changeOutputStreamToFile();

        long filterTimeStart = Instant.now().toEpochMilli();

        List<Map.Entry<String, Integer>> top3words = hashMap.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getValue, reverseOrder()))
                .limit(3)
                .collect(Collectors.toList());

        long filterTimeEnd = Instant.now().toEpochMilli();
        long totalTime = populateTime + (filterTimeEnd - filterTimeStart);

        System.out.println("Time taken to populate HashMap with all words: " + populateTime);
        System.out.println("Time taken to get the top 3 words: " + (filterTimeEnd - filterTimeStart));

        System.out.println("Total time taken: " + totalTime);

        System.out.println("\nThe top 3 words: ");
        for (Map.Entry entry : top3words) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }


    /**
     * Changes the PrintStream to a file, to allow ease saving of results
     */
    private void changeOutputStreamToFile() {
        if (printStream != null) {
            return;
        }

        String fileOutput = "resources/output.txt";
        try {
            printStream = new PrintStream(new FileOutputStream(fileOutput), true);
            System.setOut(printStream);
        } catch (FileNotFoundException e) {
            System.out.println(fileOutput + " not found " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void displayExceptionMessage(String message) {
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
                e.printStackTrace();
            }
        }
    }
}
