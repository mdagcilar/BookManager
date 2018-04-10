package com.m3c.md.model;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * BookManagerParallelTest
 *
 * @author Metin Dagcilar
 * @version 1.0
 * @since 2018-04-03
 */

public class BookManagerParallelTest {

    private static org.apache.log4j.Logger logger = Logger.getLogger(BookManagerParallelTest.class);

    private BookManager bookManagerParallel;

    @BeforeClass
    public static void setup() {
        PropertyConfigurator.configure(Constants.LOG_PROPERTIES_FILE);
        logger.info("Begin running tests");
    }

    @AfterClass
    public static void finish() {
        logger.info("Tests have finished");
    }

    @Before
    public void setupBeforeEachMethod() {
        bookManagerParallel = new BookManagerParallel();
    }

    //TODO
    @Test
    public void findTopThreeWords() throws BookManagerException {
        List<Map.Entry<String, Integer>> expectedTopThree = new ArrayList<>();
        expectedTopThree.add(new java.util.AbstractMap.SimpleEntry<>("the", 732));
        expectedTopThree.add(new java.util.AbstractMap.SimpleEntry<>("of", 446));
        expectedTopThree.add(new java.util.AbstractMap.SimpleEntry<>("to", 421));

        List<Map.Entry<String, Integer>> topThree = bookManagerParallel.getTopThreeWords(Constants.TURING_FILE, false);
        assertTrue(topThree.equals(expectedTopThree));
    }

    @Test(expected = BookManagerException.class)
    public void fileDoesNotExist() throws BookManagerException {
        bookManagerParallel.getTopThreeWords("nosuchfile", false);
    }
}