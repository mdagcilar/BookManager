package com.m3c.md.java8version;

import com.m3c.md.controller.BookManager;
import com.m3c.md.view.DisplayManager;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * BookManagerParallel
 *
 * @author Metin Dagcilar
 * @version 1.0
 * @since 2018-04-03
 */

public class BookManagerParallel implements BookManager {

    private Map<String, Integer> wordsHashMap = new ConcurrentHashMap<>();

    /**
     * Finds the top 3 words in a txt file.
     *
     * @param filePath - path to the file
     */
    public void findTopThreeWords(String filePath) {
        long populateTimeStart = Instant.now().toEpochMilli();
        wordsHashMap = readFile(filePath);
        long populateTimeEnd = Instant.now().toEpochMilli();

        DisplayManager.printTopThreeWords(wordsHashMap, (populateTimeEnd - populateTimeStart), false);
    }


    private Map<String, Integer> readFile(String filePath) {
        Path path = Paths.get(filePath);

        try {
            Files.readAllLines(path, StandardCharsets.ISO_8859_1)
                    .parallelStream()                                                               // Start streaming the lines
                    .map(line -> line.toLowerCase().replaceAll("[^a-z]+", " "))                     // Split line into individual words
                    .collect(Collectors.toConcurrentMap(line -> line, count -> 1, Integer::sum))    // Convert stream of String[] to stream of String
                    .forEach((line, count) -> {
                        String[] wordsFromLine = line.split("\\s");
                        for (String word : wordsFromLine) {
                            if (wordsHashMap.containsKey(word)) {
                                wordsHashMap.put(word, wordsHashMap.get(word) + 1);     // get word and increment counter
                            } else {
                                wordsHashMap.put(word, 1);                              // insert new word into the HashMap
                            }
                        }
                    });
            return wordsHashMap;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

//    private Map<String, Integer> readFile_2(String filePath) {
//        Path path = Paths.get(filePath);
//
//        try {
//            Files.readAllLines(path, StandardCharsets.ISO_8859_1)
//                    .parallelStream()                                                               // Start streaming the lines
//                    .map(line -> line.toLowerCase().replaceAll("[^a-z]+", ""))    // Split line into individual words
//                    .flatMap(Arrays::stream)                                                        // Convert stream of String[] to stream of String
//                    .parallel()                                                                     // convert to parallel stream
//                    .forEach(word -> {
//                        if (wordsHashMap.containsKey(word))
//                            wordsHashMap.put(word, wordsHashMap.get(word) + 1);                    // get word and increment counter
//                        wordsHashMap.put(word, 1);                                                 // insert new word into the HashMap
//                    });
//            return wordsHashMap;
//        } catch (
//                IOException e)
//
//        {
//            e.printStackTrace();
//        }
//        return null;
//    }
}


