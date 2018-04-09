package com.m3c.md.model.preprocessing;

import com.m3c.md.model.BookManager;
import edu.stanford.nlp.ling.DocumentReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Remove numbers
 * Remove punctuation
 * Convert to lowercase
 * Remove English stop words
 * Stem words
 */

public class BookManagerPreprocessed implements BookManager {

    @Override
    public void findTopThreeWords(String filePath) {
        tokenization(filePath);

    }

    /**
     * Cleans the input files, preparing it for processing
     *
     * @param filePath - path to file
     */
    private void tokenization(String filePath) {
        BufferedReader bufferedReader;

        try {
            bufferedReader = new BufferedReader(new FileReader(filePath));

            DocumentReader documentReader = new DocumentReader(bufferedReader);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
