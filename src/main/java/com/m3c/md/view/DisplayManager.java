package com.m3c.md.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.Instant;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static java.util.Collections.reverseOrder;

public class DisplayManager {

    /**
     * Prints to standard output the top 3 occurring words
     *
     * @param hashMap - the HashMap containing words and their counts.
     */
    public static void printTopThreeWords(HashMap<String, Integer> hashMap, long populateTime) {
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
     * Prints to standard output the top 3 occurring words
     *
     * @param hashMap - the HashMap containing words and their counts.
     */
    public static void printTopThreeWords(ConcurrentHashMap<String, Integer> hashMap, long populateTime) {
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
