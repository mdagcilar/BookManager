package com.m3c.md.controller;

import com.m3c.md.view.DisplayManager;

import java.io.*;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * BookManagerImpl
 *
 * @author Metin Dagcilar
 * @version 1.0
 * @since 2018-04-03
 */

public class BookManagerImpl implements BookManager {

    private Map<String, Integer> wordsHashMap = new HashMap<>();
    private DisplayManager displayManager = new DisplayManager();

    /**
     * Finds the top 3 words in a txt file.
     *
     * @param filePath - path to the file
     */
    public void findTopThreeWords(String filePath) {
        long populateTimeStart = Instant.now().toEpochMilli();
        populateWordsHashMap(filePath);
        long populateTimeEnd = Instant.now().toEpochMilli();

        DisplayManager.printTopThreeWords(wordsHashMap, (populateTimeEnd - populateTimeStart), true);
    }

    /**
     * Iterates through a file and inserts a K,V pair of words and an associated counter <Word, Counter>
     *
     * @param filePath - path to the file
     */
    private void populateWordsHashMap(String filePath) {
        BufferedReader bufferedReader;

        try {
            bufferedReader = new BufferedReader(new FileReader(filePath));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                //split the line
                for (String word : splitWords(line)) {
                    if (wordsHashMap.containsKey(word)) {
                        wordsHashMap.put(word, wordsHashMap.get(word) + 1);     // get word and increment counter
                    } else {
                        wordsHashMap.put(word, 1);                              // insert new word into the HashMap
                    }
                }
            }
        } catch (FileNotFoundException e) {
            displayManager.displayExceptionMessage("File not found exception: " + e.getMessage());
        } catch (IOException e) {
            displayManager.displayExceptionMessage("IOException: " + e.getMessage());
        }
    }

    /**
     * Given a string, split the string into words. Using a regex
     * which accepts only characters with are from a-z. Ignoring punctuation and numerical input.
     *
     * @param line The String to split
     * @return Array of Strings
     */
    private String[] splitWords(String line) {
        return line.toLowerCase().split("[^a-z]+");
    }
}
