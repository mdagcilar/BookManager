package com.m3c.md.model;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class BookManagerImplTest {

    private static org.apache.log4j.Logger logger = Logger.getLogger(BookManagerImplTest.class);
    private static final String TURING = "resources/turing.txt";

    @BeforeClass
    public static void setup(){
        logger.info("Begin running tests");
    }

    @AfterClass
    public static void finish(){
        logger.info("Tests have finished");
    }

    @Test
    public void findTopThreeWords() throws BookManagerException {
        BookManager bookManager = new BookManagerImpl();
        bookManager.findTopThreeWords(TURING);
    }

    @Test(expected = BookManagerException.class)
    public void fileDoesNotExist() throws BookManagerException {
        BookManager bookManager = new BookManagerImpl();
        bookManager.findTopThreeWords("nosuchfile");
    }


}