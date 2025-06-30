package com.ltsllc.prior;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
        String[] strings = {
                "1\n",
                "\n"
        };
        ByteArrayBuilder byteArrayBuilder = new ByteArrayBuilder();
        byteArrayBuilder.add(strings);
        Prior.inputStream = new ByteArrayInputStream(byteArrayBuilder.toByteArray());
        Scanner scanner = new Scanner(Prior.inputStream);

        int answer = itemOne.prioritize(itemTwo, scanner);

        assert (answer == 1 || answer == 2);
        assert (answer == 1);
    }

    @Test
    void newReason() {
        //
         // no reason
        //
        String[] strings = {
                "/n"
        };
        ByteArrayBuilder byteArrayBuilder = new ByteArrayBuilder();
        byteArrayBuilder.add(strings);
        Prior.inputStream = new ByteArrayInputStream(byteArrayBuilder.toByteArray());
        Scanner scanner = new Scanner(Prior.inputStream);

        Item item = new Item();
        item.newReason(scanner);

        //
         // a new reason
        //
        strings = new String[] {
                "Because it's number one\n"
        };
        byteArrayBuilder = new ByteArrayBuilder();
        byteArrayBuilder.add(strings);
        Prior.inputStream = new ByteArrayInputStream(byteArrayBuilder.toByteArray());
        scanner = new Scanner(Prior.inputStream);
        item.newReason(scanner);
    }

    @Test
    void printReasons() {
        //
         // no reasons
        //
        Item item = new Item();
        item.printReasons();

        //
         // a reason
        //
        item.getReasons().add("Because it's number one");
        item.printReasons();
    }

    @Test
    void write() {
        File file = new File("temp.txt");
        if (file.exists()) {
            file.delete();
        }

        Item item = new Item("one");

        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(file);
        } catch (IOException e) {
            throw new RuntimeException("could not open file, " + file + " for witing", e);
        }

        item.write(fileWriter);

        try {
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("could not close FileWiter", e);
        }

        assert (file.exists());
    }

    @Test
    void compareTo() {
        Item itemOne = new Item("one");
        Item itemTwo = new Item("two");

        String[] strings = {
                "1\n",
                "\n"
        };
        ByteArrayBuilder byteArrayBuilder = new ByteArrayBuilder();
        byteArrayBuilder.add(strings);
        Prior prior = new Prior();
        Prior.inputStream = new ByteArrayInputStream(byteArrayBuilder.toByteArray());
        Scanner scanner = new Scanner(Prior.inputStream);

        int result = itemOne.compareTo(itemTwo, scanner);

        assert (result == 1);

        strings = new String[] {
                "2\n",
                "\n"
        };
        byteArrayBuilder = new ByteArrayBuilder();
        byteArrayBuilder.add(strings);
        Prior.inputStream = new ByteArrayInputStream(byteArrayBuilder.toByteArray());
        scanner = new Scanner(Prior.inputStream);

        result = itemOne.compareTo(itemTwo, scanner);

        assert (result == 2);
    }
}