package com.ltsllc.prior;

import java.util.Comparator;

public class ItemComparator implements Comparator<Item> {
    @Override
    public int compare(Item o1, Item o2) {
        if (o1.getScore() > o2.getScore()) {
            return -1;
        } else if (o2.getScore() > o1.getScore()) {
            return 1;
        } else if (o1.getScore() == o2.getScore()) {
            if (o1.isBefore(o2)) {
                return -1;
            } else {
                return 1;
            }
        } else {
            throw new RuntimeException("impossible case");
        }
    }
}
