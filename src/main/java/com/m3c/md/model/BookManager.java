package com.m3c.md.model;

import java.util.Map;

/**
 * BookManager interface
 *
 * @author Metin Dagcilar
 * @version 1.0
 * @since 2018-04-03
 */

public interface BookManager {

    void findTopThreeWords(String exampleFile) throws BookManagerException;
}
