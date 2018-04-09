package com.m3c.md.controller;

import com.m3c.md.java8version.BookManagerParallel;
import com.m3c.md.preprocessing.BookManagerPreprocessed;

public class Starter {

    private static final String EXAMPLE_FILE = "resources/example.txt";
    private static final String A_LARGE_FILE = "resources/aLargeFile";

    public static void main(String[] args) {
        BookManager bookManager = new BookManagerPreprocessed();
        bookManager.findTopThreeWords(EXAMPLE_FILE);

//        //Run process and store results in a file
//        for (int i = 0; i < 20; i++) {
//            System.out.println(i);
//            bookManager.findTopThreeWords(EXAMPLE_FILE);
//        }


        // Java 8 parallel
//        BookManagerParallel bookManagerParallel = new BookManagerParallel();
//        bookManagerParallel.findTopThreeWords(EXAMPLE_FILE);
    }
}
