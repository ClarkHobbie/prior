package com.ltsllc.prior;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class PriorTest {

    @Test
    void main() {
        String[] strings = {
                "1\n",
                "1\n"
        };
        ByteArrayBuilder byteArrayBuilder = new ByteArrayBuilder();
        byteArrayBuilder.add(strings);
        Prior.inputStream = new ByteArrayInputStream(byteArrayBuilder.toByteArray());
        String[] args = { "temp.txt" };
        Prior.main(args);
        TextFile textFile = new TextFile(new File("temp.txt"));
        textFile.load();
        assert (textFile.getText().get(0).equalsIgnoreCase("one"));
    }


    @Test
    void readFile() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("one");
        strings.add("two");

        Prior main = new Prior();
        Path path = Paths.get ("test.txt");
        List<Item> items = main.readFile(path);

        assert (items.get(0).getItemName().equalsIgnoreCase("one"));
    }

    @Test
    void prioritise() {
        ArrayList<Item> arrayList = new ArrayList<>();

        String[] buff = { "1\n", "\n" };
        Prior main = new Prior();
        ByteArrayBuilder byteArrayBuilder = new ByteArrayBuilder();
        byteArrayBuilder.add(buff);
        Prior.inputStream = new ByteArrayInputStream(byteArrayBuilder.toByteArray());
        Scanner scanner = new Scanner(Prior.inputStream);

        Item itemOne = new Item("one");
        arrayList.add(itemOne);

        Item itemTwo = new Item ("two");
        arrayList.add(itemTwo);

        main.prioritise(arrayList, scanner);

        assert (arrayList.get(0).getItemName().equalsIgnoreCase("one"));

        arrayList.clear();
        arrayList.add(itemOne);
        arrayList.add(itemTwo);

        buff = new String[]{
                "1\n",
                "\n"
        };
        byteArrayBuilder = new ByteArrayBuilder();
        byteArrayBuilder.add(buff);
        Prior.inputStream = new ByteArrayInputStream(byteArrayBuilder.toByteArray());
        scanner = new Scanner(Prior.inputStream);

        main.prioritise(arrayList, scanner);

        assert (arrayList.get(0).getItemName().equalsIgnoreCase("one"));
    }

    @BeforeEach
    void setUp() {
        String[] text = {
                "one",
                "\tbecause it's number one",
                "two"
        };

        TextFile textFile = new TextFile(new File("temp.txt"));
        textFile.setText(text);
        textFile.write();
    }

    @AfterEach
    void tearDown() {
        TextFile textFile = new TextFile(new File("temp.txt"));
        textFile.delete();
    }

    @Test
    void parseItem() {
    }
}