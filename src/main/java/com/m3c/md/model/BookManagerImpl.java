package com.m3c.md.model;

import com.m3c.md.view.DisplayManager;
import org.apache.log4j.Logger;

import java.io.*;
import java.time.Instant;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Collections.reverseOrder;

/**
 * BookManagerImpl
 *
 * @author Metin Dagcilar
 * @version 1.0
 * @since 2018-04-03
 */

public class BookManagerImpl implements BookManager {

    private static org.apache.log4j.Logger logger = Logger.getLogger(BookManagerImpl.class);

    private Map<String, Integer> wordCountMap;
    private DisplayManager displayManager = new DisplayManager();

    /**
     * Finds the top 3 words in a txt file.
     *
     * @param filePath - path to the file
     */
    public List<Map.Entry<String, Integer>> getTopThreeWords(String filePath, boolean printToFile) throws BookManagerException {
        long populateTimeStart = Instant.now().toEpochMilli();
        wordCountMap = new HashMap<>();
        wordCountMap = readFile(filePath);
        long populateTimeEnd = Instant.now().toEpochMilli();

        long filterTimeStart = Instant.now().toEpochMilli();
        List<Map.Entry<String, Integer>> topThreeWordsList = wordCountMap.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getValue, reverseOrder()))
                .limit(3)
                .collect(Collectors.toList());
        long filterTimeEnd = Instant.now().toEpochMilli();

        long totalTime = (populateTimeEnd - populateTimeStart) + (filterTimeEnd - filterTimeStart);
        displayManager.printTopThreeWords(topThreeWordsList, totalTime,
                (filterTimeEnd - filterTimeStart), (populateTimeEnd - populateTimeStart), printToFile);

        return topThreeWordsList;
    }

    /**
     * Iterates through a file and inserts a K,V pair of words and an associated counter <Word, Counter>
     *
     * @param filePath - path to the file
     */
    private Map<String, Integer> readFile(String filePath) throws BookManagerException {
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(filePath));
            logger.debug("BufferedReader stream open for: " + filePath);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                //split the line
                for (String word : splitWords(line)) {
                    if (wordCountMap.containsKey(word)) {
                        wordCountMap.put(word, wordCountMap.get(word) + 1);     // get word and increment counter
                    } else {
                        wordCountMap.put(word, 1);                              // insert new word into the HashMap
                    }
                }
            }
            bufferedReader.close();
            logger.debug("BufferedReader stream closed for: " + filePath);
            return wordCountMap;
        } catch (IOException e) {
            logger.error(Constants.FILE_NOT_FOUND + e.getMessage());
            throw new BookManagerException(Constants.FILE_NOT_FOUND + " :" + e.getMessage());
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
