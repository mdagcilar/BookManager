package com.m3c.md.controller;

import com.m3c.md.model.BookManager;
import com.m3c.md.model.BookManagerException;
import com.m3c.md.model.BookManagerImpl;
import com.m3c.md.model.BookManagerParallel;
import com.m3c.md.model.preprocessing.BookManagerPreprocessed;
import com.m3c.md.view.DisplayManager;

public class Starter {

    private static final String EXAMPLE_FILE = "resources/example.txt";
    private static final String TURING = "resources/turing.txt";
    private static final String A_LARGE_FILE = "resources/aLargeFile";
    private DisplayManager displayManager = new DisplayManager();


    public static void main(String[] args) {
        Starter starter = new Starter();

//        starter.runBookManagerImpl();
//        starter.parallelVersion();
//        starter.preprocessedVersion();
    }

    // Normal version using a BufferedReader
    private void runBookManagerImpl() {
        BookManager bookManagerImpl = new BookManagerImpl();

        //Run process and store results in a file
        for (int i = 0; i < 20; i++) {
            System.out.println(i);
            try {
                bookManagerImpl.findTopThreeWords(TURING);
            } catch (BookManagerException e) {
                displayManager.displayExceptionMessage(e.getMessage());
                System.exit(1);
            }
        }
    }

//    // Java 8 parallel
//    private void parallelVersion() {
//        BookManager bookManagerParallel = new BookManagerParallel();
//
//        //Run process and store results in a file
//        for (int i = 0; i < 20; i++) {
//            System.out.println(i);
//            bookManagerParallel.findTopThreeWords(TURING);
//        }
//    }
//
//    // Prepossessing on the data
//    private void preprocessedVersion() {
//        BookManager bookManagerPreprocessed = new BookManagerPreprocessed();
//
//        //Run process and store results in a file
//        for (int i = 0; i < 20; i++) {
//            System.out.println(i);
//            bookManagerPreprocessed.findTopThreeWords(TURING);
//        }
//    }
}
