package com.m3c.md.controller;

import com.m3c.md.view.DisplayManager;

import java.io.*;
import java.time.Instant;
import java.util.HashMap;

/**
 * Author: Metin Dagcilar
 */

public class BookManager {

    private HashMap<String, Integer> wordsHashMap = new HashMap<>();

    /**
     * Finds the top 3 words in a txt file.
     *
     * @param filePath - path to the file
     */
    public void findTopThreeWords(String filePath) {
        long populateTimeStart = Instant.now().toEpochMilli();
        populateWordsHashMap(filePath);
        long populateTimeEnd = Instant.now().toEpochMilli();

        DisplayManager.printTopThreeWords(wordsHashMap, (populateTimeEnd - populateTimeStart));
    }

    /**
     * Iterates through a file and inserts a word (Key) with a counter
     *
     * @param filePath - path to the file
     * @return HashMap containing the words
     */
    private HashMap<String, Integer> populateWordsHashMap(String filePath) {
        BufferedReader bufferedReader;

        try {
            bufferedReader = new BufferedReader(new FileReader(filePath));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                //split the line
                for (String word : splitLine(line)) {
                    if (wordsHashMap.containsKey(word)) {
                        wordsHashMap.put(word, wordsHashMap.get(word) + 1);     // get word and increment counter
                    } else {
                        wordsHashMap.put(word, 1);                              // insert new word into the HashMap
                    }
                }
            }
            return wordsHashMap;
        } catch (FileNotFoundException e) {
            //TODO: throw useful exception
            System.out.println("File not found exception " + e.getMessage());
        } catch (IOException e) {
            //TODO: throw useful exception
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Given a string, split the string into words. Using a regex
     * which
     *
     * @param line The String to split
     * @return Array of Strings
     */
    private String[] splitLine(String line) {
        return line.toLowerCase().split("[^a-z]+");
    }
}
