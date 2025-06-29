package com.ltsllc.prior;

import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class ItemComparatorTest {

    @Test
    void compare() {
        Item itemOne = new Item("one");
        itemOne.setScore(1);

        Item itemTwo = new Item("two");
        itemOne.setIsBefore(itemTwo, true);

        Comparator<Item> comparator = new ItemComparator();
        int result = comparator.compare(itemOne, itemTwo);
        assert (result > 0);

        result = comparator.compare(itemTwo, itemOne);
        assert (result < 0);
    }
}