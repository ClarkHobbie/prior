package com.ltsllc.prior;

import org.junit.jupiter.api.Test;


import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

class MainTest {

    @Test
    void main() {
    }


    @Test
    void readFile() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("one");
        strings.add("two");

        Main main = new Main();
        Path path = Paths.get ("test.txt");
        List<Item> items = main.readFile(path);

        assert (items.get(0).getItemName().equalsIgnoreCase("one"));

    }

    @Test
    void prioritise() {
        ArrayList<Item> arrayList = new ArrayList<>();

        byte[] buff = { '2', '\n' };
        Main main = new Main();
        main.inputStream = new ByteArrayInputStream(buff);

        Item itemOne = new Item("one");
        arrayList.add(itemOne);

        Item itemTwo = new Item ("two");
        arrayList.add(itemTwo);

        main.prioritise(arrayList, new ByteArrayInputStream(buff));

        assert (arrayList.get(0).getItemName().equalsIgnoreCase("two"));

        arrayList.clear();
        arrayList.add(itemOne);
        arrayList.add(itemTwo);

        buff = new byte[]{'1', '\n'};
        main.prioritise(arrayList, new ByteArrayInputStream(buff));

        assert (arrayList.get(0).getItemName().equalsIgnoreCase("one"));
    }
}