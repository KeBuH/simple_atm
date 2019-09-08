package ru.tretyakov.atm;

/**
 * @author A.Tretyakov.
 * @since 07.09.19
 */
public enum Banknote {

    FIFTY(50),
    HUNDRED(100),
    FIVE_HUNDRED(500),
    THOUSAND(1000),
    TWO_THOUSAND(2000),
    FIVE_THOUSAND(5000);

    public final int value;

    Banknote(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}