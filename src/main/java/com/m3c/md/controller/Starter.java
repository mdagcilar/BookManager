package com.m3c.md.controller;

import com.m3c.md.java8version.BookManagerParallel;

public class Starter {

    private static final String EXAMPLE_FILE = "resources/example.txt";
    private static final String A_LARGE_FILE = "resources/aLargeFile";

    public static void main(String[] args) {
//        BookManager bookManager = new BookManager();
//        bookManager.findTopThreeWords(A_LARGE_FILE);

        // Java 8 parallel
        BookManagerParallel bookManagerParallel = new BookManagerParallel();
        bookManagerParallel.findTopThreeWordsParallel(EXAMPLE_FILE);
    }
}
