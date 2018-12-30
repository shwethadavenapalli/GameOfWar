package com.model;

/**
 * Created by Shwetha on 27-12-2018.
 */
public enum SuiteValue {
    TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10),
    JACK(11), Queen(12), KING(13), ACE(14);

    private final int value;

    SuiteValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }


}
