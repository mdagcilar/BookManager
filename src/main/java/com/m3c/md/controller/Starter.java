package com.m3c.md.controller;

import com.m3c.md.model.*;
import com.m3c.md.view.DisplayManager;
import org.apache.log4j.PropertyConfigurator;

public class Starter {

    private final String filePath = Constants.A_LARGE_FILE; // path to file

    public static void main(String[] args) {
        Starter starter = new Starter();
        initialiseLogger();

        starter.runBookManagerImpl();
        starter.runBookManagerParallel();
    }

    private static void initialiseLogger() {
        PropertyConfigurator.configure(Constants.LOG_PROPERTIES_FILE);
    }

    private void runBookManagerImpl() {
        BookManager bookManagerImpl = new BookManagerImpl();

        try {
            System.out.println("BookManager non-parallel version on - " + filePath);
            bookManagerImpl.getTopThreeWords(filePath, false);
        } catch (BookManagerException e) {
            DisplayManager.displayExceptionMessage(e.getMessage());
            System.exit(1);
        }
    }

    private void runBookManagerParallel() {
        BookManager bookManagerParallel = new BookManagerParallel();

        try {
            System.out.println("BookManager Parallel version on - " + filePath);
            bookManagerParallel.getTopThreeWords(filePath, false);
        } catch (BookManagerException e) {
            DisplayManager.displayExceptionMessage(e.getMessage());
            System.exit(1);
        }
    }
}
