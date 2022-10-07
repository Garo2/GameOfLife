package org.peAccounting.utils;

import java.util.Random;

/**
 * Utilities
 */
public class Utilities {

    /**
     * generate a random number between @lowerBound and @upperBound
     * @param lowerBound the least possible value to be randomized (lowerBound value is included)
     * @param upperBound the greatest possible value to be randomized (upperBound value is excluded)
     * @return the randomized number.
     */
    public static int generateRandomInBound(int lowerBound, int upperBound) {
        Random rnd = new Random();
        return rnd.nextInt(lowerBound, upperBound);
    }
}
