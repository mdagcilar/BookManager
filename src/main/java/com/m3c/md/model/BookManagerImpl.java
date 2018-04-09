package com.m3c.md.model;

import com.m3c.md.view.DisplayManager;
import org.apache.log4j.Logger;

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

    private static final String FILE_NOT_FOUND = "File not found";
    private static org.apache.log4j.Logger logger = Logger.getLogger(BookManagerImpl.class);

    private Map<String, Integer> wordsHashMap;
    private DisplayManager displayManager = new DisplayManager();

    /**
     * Finds the top 3 words in a txt file.
     *
     * @param filePath - path to the file
     */
    public void findTopThreeWords(String filePath) throws BookManagerException {
        long populateTimeStart = Instant.now().toEpochMilli();

        wordsHashMap = new HashMap<>();
        wordsHashMap = readFile(filePath);

        long populateTimeEnd = Instant.now().toEpochMilli();

        displayManager.printTopThreeWords(wordsHashMap, (populateTimeEnd - populateTimeStart), true);
    }

    /**
     * Iterates through a file and inserts a K,V pair of words and an associated counter <Word, Counter>
     *
     * @param filePath - path to the file
     */
    private Map<String, Integer> readFile(String filePath) throws BookManagerException {

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            logger.debug("BufferedReader stream open for: " + filePath);

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
            bufferedReader.close();
            logger.debug("BufferedReader stream closed for: " + filePath);
            return wordsHashMap;
        } catch (IOException e) {
            logger.error(FILE_NOT_FOUND + e.getMessage());
            throw new BookManagerException(FILE_NOT_FOUND + " :" + e.getMessage());
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
