package com.m3c.md.model;

import java.util.List;
import java.util.Map;

/**
 * BookManager interface
 *
 * @author Metin Dagcilar
 * @version 1.0
 * @since 2018-04-03
 */

public interface BookManager {

    List<Map.Entry<String, Integer>> getTopThreeWords(String exampleFile) throws BookManagerException;
}
