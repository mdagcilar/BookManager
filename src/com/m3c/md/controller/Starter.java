package com.m3c.md.controller;

import java.time.Instant;

public class Starter {

    private static final String EXAMPLE_FILE = "resources/example.txt";
    private static final String A_LARGE_FILE = "resources/aLargeFile";

    public static void main(String[] args) {

        // Run process and store results in a file
//        for (int i = 0; i < 20; i++) {
//            long populateTimeStart = Instant.now().toEpochMilli();
//            BookManager bookManager = new BookManager();
//            bookManager.findTopThreeWords(EXAMPLE_FILE);
//            long populateTimeEnd = Instant.now().toEpochMilli();
//            System.out.println(i + " - Total time taken: " + (populateTimeEnd - populateTimeStart));
//        }

        BookManager bookManager = new BookManagerImpl();
        bookManager.findTopThreeWords(EXAMPLE_FILE);

        // Java 8 parallel
        //BookManagerParallel bookManagerParallel = new BookManagerParallel();
        //bookManagerParallel.findTopThreeWordsParallel(EXAMPLE_FILE);
    }
}
