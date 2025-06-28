package com.ltsllc.prior;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Scanner;

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
        assert (item.getReasons().get(0).equalsIgnoreCase("Because it's number one"));
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

    @Test
    void prioritizeNewReason () {
        Item itemOne = new Item("one");
        Item itemTwo = new Item("two");

        String[] strings = { "1\n", "Because it's number one\n"};
        ByteArrayBuilder byteBuilder = new ByteArrayBuilder();

        for (int i = 0; i < strings.length; i++) {
            byteBuilder.add(strings[i]);
        }

        byte[] buff = byteBuilder.toByteArray();
        Prior.inputStream = new ByteArrayInputStream(buff);
        Scanner scanner = new Scanner(Prior.inputStream);
        itemOne.prioritize(itemTwo, scanner);

        assert (itemOne.reasons.size() == 1);
        assert (itemOne.getReasons().get(0).equalsIgnoreCase("Because it's number one"));
    }

    @Test
    void prioritize() {
        Item itemOne = new Item("one");
        itemOne.addReason("because it's number one");
        Item itemTwo = new Item("two");
        byte[] buff = { '1', '\n', '1', '\n'};
        Prior.inputStream = new ByteArrayInputStream(buff);
        Scanner scanner = new Scanner(Prior.inputStream);

        int answer = itemOne.prioritize(itemTwo, scanner);

        assert (answer == 1 || answer == 2);
        assert (answer == 1);
    }
}