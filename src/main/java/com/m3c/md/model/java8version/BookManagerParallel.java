package com.m3c.md.model.java8version;

import com.m3c.md.model.BookManager;
import com.m3c.md.model.BookManagerException;
import com.m3c.md.view.DisplayManager;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * BookManagerParallel
 *
 * @author Metin Dagcilar
 * @version 1.0
 * @since 2018-04-03
 */

public class BookManagerParallel implements BookManager {

    private static final String FILE_NOT_FOUND = "File not found";
    private Map<String, Integer> wordCountMap = new ConcurrentHashMap<>();

    /**
     * Finds the top 3 words in a txt file.
     *
     * @param filePath - path to the file
     */
    public void findTopThreeWords(String filePath) {
        long populateTimeStart = Instant.now().toEpochMilli();

        try {
            wordCountMap = readFile(filePath);
        } catch (BookManagerException e) {
            DisplayManager.displayExceptionMessage(e.getMessage());
            System.exit(1);
        }

        long populateTimeEnd = Instant.now().toEpochMilli();

        DisplayManager.printTopThreeWords(wordCountMap, (populateTimeEnd - populateTimeStart), false);
    }

    /**
     * Reads all lines from a file as a stream in parallel.
     * Excludes everything that isn't a string of characters a-z
     * splits the line into words, then groups by their occurrence.
     *
     * @param filePath - path to the file
     * @return HashMap of <Word, Count>
     */
    private Map<String, Integer> readFile(String filePath) throws BookManagerException {
        Path path = Paths.get(filePath);

        try {
            return wordCountMap =
                    Files.lines(path, StandardCharsets.ISO_8859_1)              // Read all lines from a file as a Stream.
                            .parallel()                                         // Returns an equivalent stream that is parallel
                            .map(line -> line.toLowerCase().replaceAll("[^a-z]+", " "))  // replace everything that isn't a word, with the empty space
                            .flatMap(Pattern.compile("\\s+")::splitAsStream)    // Splits the line by spaces, into words
                            .collect(Collectors.groupingBy(w -> w,              // group words by their occurrence and store their count
                                    Collectors.summingInt(w -> 1)));
        } catch (IOException e) {
            throw new BookManagerException(FILE_NOT_FOUND + " :" +  e.getMessage());
        }
    }


    /**
     * TODO: currently runs out of heap space on aLargeFile
     * readAllLines() and parallelStream() are the causes of the heap space running out
     *
     * @param filePath - path to file
     * @return
     */
    private Map<String, Integer> readFile_2(String filePath) throws BookManagerException {
        Path path = Paths.get(filePath);

        try {
            Files.readAllLines(path, StandardCharsets.ISO_8859_1)
                    .parallelStream()                                                               // Start streaming the lines
                    .map(line -> line.toLowerCase().replaceAll("[^a-z]+", " "))   // Split line into individual words
                    .collect(Collectors.toConcurrentMap(line -> line, count -> 1, Integer::sum))
                    .forEach((line, count) -> {
                        String[] wordsFromLine = line.split("\\s");
                        for (String word : wordsFromLine) {
                            if (wordCountMap.containsKey(word)) {
                                wordCountMap.put(word, wordCountMap.get(word) + 1);                 // get word and increment counter
                            } else {
                                wordCountMap.put(word, 1);                                          // insert new word into the HashMap
                            }
                        }
                    });
            return wordCountMap;
        } catch (IOException e) {
            throw new BookManagerException(FILE_NOT_FOUND);
        }
    }
}
