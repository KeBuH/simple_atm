package ru.tretyakov.atm;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ATMTest {

    private ATM atm;

    @Test(expected = RuntimeException.class)
    public void test001_get_money_from_empty_atm() {
        this.atm = new ATM();
        Map<Banknote, Integer> money = new HashMap<>();
        money.put(Banknote.FIFTY, 0);
        money.put(Banknote.HUNDRED, 0);
        money.put(Banknote.FIVE_HUNDRED, 0);

        this.atm.putMoney(money);
        this.atm.getMoney(1200);
    }

    @Test(expected = RuntimeException.class)
    public void test002_get_money() {
        this.atm = new ATM();
        Map<Banknote, Integer> money = new HashMap<>();
        money.put(Banknote.FIFTY, 10);
        money.put(Banknote.HUNDRED, 10);
        money.put(Banknote.FIVE_HUNDRED, 0);

        this.atm.putMoney(money);
        Map result = this.atm.getMoney(1200);

        assertThat(result.get(Banknote.HUNDRED), is(10));
        assertThat(result.get(Banknote.FIFTY), is(4));
    }

}