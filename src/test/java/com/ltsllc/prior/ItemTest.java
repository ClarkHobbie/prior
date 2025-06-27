package com.ltsllc.prior;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    @Test
    void parse1() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("one");
        arrayList.add("    Because it's number one");
        arrayList.add("two");

        Item item = new Item();
        ListIndex<String> listIndex = new ListIndex<>(arrayList, 0);
        item.parse(listIndex);

        assert (listIndex.index > 0);
        assert (item.getItemName().equalsIgnoreCase("one"));
        assert (item.getReasons().size() == 1);
        assert (item.getReasons().get(0).equalsIgnoreCase("    Because it's number one"));
    }

    @Test
    void parse2() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("one");
        arrayList.add("two");

        ListIndex<String> listIndex = new ListIndex<>(arrayList, 0);
        Item item = new Item();
        item.parse(listIndex);

        assert (item.getItemName().equalsIgnoreCase("one"));
        assert (item.getReasons().size() == 0);
    }
}