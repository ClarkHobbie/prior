package com.ltsllc.prior;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class MainTest {

    @Test
    void main() {
    }

    @Test
    void parseItem() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("one");
        arrayList.add("    Because it's number one");
        arrayList.add("two");

        ListIndex<String> listIndex = new ListIndex<>(arrayList, 0);
        Item item = new Item(arrayList.get(0));

        assert (item.getReasons().size() <= 0);
        assert (item.getItemName().equalsIgnoreCase("one"));
    }

    @Test
    void readFile() {
    }
}