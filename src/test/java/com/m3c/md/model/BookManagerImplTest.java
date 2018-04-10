package com.m3c.md.model;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * BookManagerImplTest
 *
 * @author Metin Dagcilar
 * @version 1.0
 * @since 2018-04-03
 */

public class BookManagerImplTest {

    private static org.apache.log4j.Logger logger = Logger.getLogger(BookManagerImplTest.class);
    private BookManager bookManager;

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
        bookManager = new BookManagerImpl();
    }

    /**
     * Tests the findTopThreeWords() method against a known test file, to check whether the
     * correct 3 words are returned and their correct number of occurrences.
     *
     * @throws BookManagerException -
     */
    @Test
    public void findTopThreeWords() throws BookManagerException {
        List<Map.Entry<String, Integer>> expectedTopThree = new ArrayList<>();
        expectedTopThree.add(new java.util.AbstractMap.SimpleEntry<>("the", 732));
        expectedTopThree.add(new java.util.AbstractMap.SimpleEntry<>("of", 446));
        expectedTopThree.add(new java.util.AbstractMap.SimpleEntry<>("to", 421));

        List<Map.Entry<String, Integer>> topThree = bookManager.getTopThreeWords(Constants.TURING_FILE, false);
        assertTrue(topThree.equals(expectedTopThree));
    }

    @Test(expected = BookManagerException.class)
    public void fileDoesNotExist() throws BookManagerException {
        bookManager.getTopThreeWords("nosuchfile", false);
    }
}
