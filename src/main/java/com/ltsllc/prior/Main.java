package com.ltsllc.prior;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        if (args.length <= 0) {
            System.out.println("usage prior <file>");
            System.exit(1);
        }

        Path path = Paths.get(args[0]);
        List<String> text = readFile(path);
        Item[] items = parseFile(text);


    }

    private static Item[] parseFile(List<String> text) {
        Iterator<String> iterator = text.iterator();
        ArrayList<Item> arrayList = new ArrayList<>();
        ListIndex<String> listIndex = new ListIndex<String>(text, 0);

        while (listIndex.index < listIndex.list.size()) {
            Item item = new Item(listIndex.list.get(listIndex.index));
            listIndex.index = listIndex.index + 1;
            item.parse(listIndex);
            arrayList.add(item);
        }

        return (Item[]) arrayList.toArray();
    }

    public static Item parseItem(ListIndex<String> listIndex) {
        Item item = new Item("one");
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("one");
        arrayList.add("two");

        listIndex.list = arrayList;
        listIndex.index = 0;

        item.parse(listIndex);

        assert (listIndex.index != 0);
        assert (item.getItemName().equalsIgnoreCase("one"));

        return item;
    }

    public static List<String>  readFile (Path path) {
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException("error reading file, " + path, e);
        }
    }
}
