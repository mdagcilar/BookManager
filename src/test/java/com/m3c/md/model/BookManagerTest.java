package com.m3c.md.model;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * BookManagerTest
 *
 * @author Metin Dagcilar
 * @version 1.0
 * @since 2018-04-03
 */

public class BookManagerTest {

    private static org.apache.log4j.Logger logger = Logger.getLogger(BookManagerImplTest.class);
    private BookManager bookManagerImpl, bookManagerParallel;

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
        bookManagerImpl = new BookManagerImpl();
        bookManagerParallel = new BookManagerParallel();
    }

    /**
     * Tests the single threaded implementation vs the parallel version to see if the results are the same
     *
     * @throws BookManagerException -
     */
    @Test
    public void findTopThreeWordsTuring() throws BookManagerException {
        List<Map.Entry<String, Integer>> topThree = bookManagerImpl.getTopThreeWords(Constants.TURING_FILE, false);

        List<Map.Entry<String, Integer>> topThreeParallel = bookManagerParallel.getTopThreeWords(Constants.TURING_FILE, false);

        assertTrue(topThree.equals(topThreeParallel));
    }
}
