package ru.tretyakov.atm;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;



/**
 * @author A.Tretyakov.
 * @since 07.09.19
 */
public class ATM {

    private Map<Banknote, Integer> storage = new HashMap<>();
    private int total = 0;

    public void putMoney(final Map<Banknote, Integer> values) {
        values.forEach((key, value) -> {
            this.total += value;
            this.addMoney(value, key, this.storage);
        });
    }

    public Map<Banknote, Integer> getMoney(final int amount) {

        AtomicInteger total = new AtomicInteger(amount);
        Map<Banknote, Integer> result = new HashMap<>();

        if (amount <= this.total) {
            this.storage.entrySet()
                    .stream()
                    .filter(e -> e.getKey().getValue() < amount)
                    .sorted((k1, k2) ->
                            Integer.compare(
                                    k2.getKey().getValue(), k1.getKey().getValue()))
                    .forEach(v -> this.processing(v.getKey(), total, result));
            return result;
        } else throw new RuntimeException("Sorry! Not enough amount!");
    }

    private void processing(final Banknote banknote,
                            final AtomicInteger amount,
                            final Map<Banknote, Integer> result) {
        while (amount.get() >= banknote.getValue()
                && storage.get(banknote) > 0 && amount.get() != 0) {
            this.total -= banknote.getValue();
            amount.set(amount.get() - banknote.getValue());
            this.storage.computeIfPresent(banknote, (k,v) -> v - 1);
            this.addMoney(1, banknote, result);
        }
    }

    private void addMoney(final Integer value,
                          final Banknote banknote,
                          final Map<Banknote, Integer> moneys) {
        if (moneys.computeIfPresent(banknote, (k,v) -> v + value) == null) {
            moneys.put(banknote, value);
        }
    }
}
