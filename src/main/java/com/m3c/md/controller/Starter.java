package com.m3c.md.controller;

import com.m3c.md.model.BookManager;
import com.m3c.md.model.BookManagerException;
import com.m3c.md.model.BookManagerImpl;
import com.m3c.md.model.Constants;
import com.m3c.md.view.DisplayManager;
import org.apache.log4j.PropertyConfigurator;

public class Starter {

    public static void main(String[] args) {
        Starter starter = new Starter();
        initialiseLogger();

        starter.runBookManagerImpl();
//        starter.parallelVersion();
//        starter.preprocessedVersion();
    }

    public static void initialiseLogger(){
        PropertyConfigurator.configure(Constants.LOG_PROPERTIES_FILE);
    }

    // Normal version using a BufferedReader
    private void runBookManagerImpl() {
        BookManager bookManagerImpl = new BookManagerImpl();

        //Run process and store results in a file
        for (int i = 0; i < 20; i++) {
            System.out.println(i);
            try {
                bookManagerImpl.getTopThreeWords(Constants.TURING_FILE);
            } catch (BookManagerException e) {
                DisplayManager.displayExceptionMessage(e.getMessage());
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
//            bookManagerParallel.getTopThreeWords(Constants.TURING_FILE);
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
//            bookManagerPreprocessed.getTopThreeWords(Constants.TURING_FILE);
//        }
//    }


}
