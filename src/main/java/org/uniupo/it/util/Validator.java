package org.uniupo.it.util;

public class Validator {

    private static final int MIN = 0;
    private static final int MAX = 9;

    public static boolean isSelectionValid(int selection) {
        return selection >= MIN && selection <= MAX;
    }
}
