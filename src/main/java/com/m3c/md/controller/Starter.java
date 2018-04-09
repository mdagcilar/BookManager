package com.m3c.md.controller;

import com.m3c.md.model.BookManager;
import com.m3c.md.model.BookManagerImpl;
import com.m3c.md.model.java8version.BookManagerParallel;
import com.m3c.md.model.preprocessing.BookManagerPreprocessed;

public class Starter {

    private static final String EXAMPLE_FILE = "resources/exampleasdas.txt";
    private static final String BOOK = "resources/books.ts";
    private static final String A_LARGE_FILE = "resources/aLargeFile";

    public static void main(String[] args) {
        Starter starter = new Starter();
        starter.runBookManagerImpl();
//        starter.parallelVersion();
//        starter.preprocessedVersion();

    }

    // Normal version using a BufferedReader
    private void runBookManagerImpl() {
        BookManager bookManagerImpl = new BookManagerImpl();

        //Run process and store results in a file
        for (int i = 0; i < 20; i++) {
            System.out.println(i);
            bookManagerImpl.findTopThreeWords(EXAMPLE_FILE);
        }
    }

    // Java 8 parallel
    private void parallelVersion() {
        BookManager bookManagerParallel = new BookManagerParallel();

        //Run process and store results in a file
        for (int i = 0; i < 20; i++) {
            System.out.println(i);
            bookManagerParallel.findTopThreeWords(BOOK);
        }
    }

    // Prepossessing on the data
    private void preprocessedVersion() {
        BookManager bookManagerPreprocessed = new BookManagerPreprocessed();

        //Run process and store results in a file
        for (int i = 0; i < 20; i++) {
            System.out.println(i);
            bookManagerPreprocessed.findTopThreeWords(EXAMPLE_FILE);
        }
    }
}
