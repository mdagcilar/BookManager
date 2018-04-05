package com.m3c.md.java8version;

import com.m3c.md.view.DisplayManager;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class BookManagerParallel {

    private ConcurrentHashMap<String, Integer> wordsHashMap = new ConcurrentHashMap<String, Integer>();

    /**
     * Finds the top 3 words in a txt file.
     *
     * @param filePath - path to the file
     */
    public void findTopThreeWordsParallel(String filePath) {
        long populateTimeStart = Instant.now().toEpochMilli();
        readFile(filePath);
        long populateTimeEnd = Instant.now().toEpochMilli();

        DisplayManager.printTopThreeWords(wordsHashMap, (populateTimeEnd - populateTimeStart));
    }

    private ConcurrentHashMap<String, Integer> readFile_2(String filePath) {
        Path path = Paths.get(filePath);

        try {
            Files.readAllLines(path, StandardCharsets.ISO_8859_1)
                    .parallelStream()
                    .map(line -> line.toLowerCase().replaceAll("[^a-z]+", ""))
                    .flatMap(Arrays::stream)
                    .parallel()
                    .forEach((line, coun) -> {
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





    private ConcurrentHashMap<String, Integer> readFile(String filePath) {
        Path path = Paths.get(filePath);

        try {
            Files.readAllLines(path, StandardCharsets.ISO_8859_1)
                    .parallelStream()
                    .map(line -> line.toLowerCase().replaceAll("[^a-z]+", ""))
                    .collect(Collectors.toConcurrentMap(line -> line, count -> 1, Integer::sum))
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
}



