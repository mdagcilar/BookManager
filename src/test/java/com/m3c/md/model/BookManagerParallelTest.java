package com.m3c.md.model;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BookManagerParallelTest {

    private static org.apache.log4j.Logger logger = Logger.getLogger(BookManagerParallelTest.class);

    private BookManager bookManagerParallel;

    @BeforeClass
    public static void setup(){
        logger.info("Begin running tests");
    }

    @AfterClass
    public static void finish(){
        logger.info("Tests have finished");
    }

    @Before
    public void setupBeforeEachMethod(){
        bookManagerParallel = new BookManagerParallel();
    }

    @Test
    public void findTopThreeWords() throws BookManagerException {
        bookManagerParallel.getTopThreeWords(Constants.TURING_FILE);
    }

    @Test(expected = BookManagerException.class)
    public void fileDoesNotExist() throws BookManagerException {
        bookManagerParallel.getTopThreeWords("nosuchfile");
    }
}