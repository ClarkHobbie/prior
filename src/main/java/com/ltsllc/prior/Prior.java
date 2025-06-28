package com.ltsllc.prior;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


public class Prior {
    public static InputStream inputStream;

    public static void main(String[] args) {
        if (args.length <= 0) {
            System.out.println("usage prior <file>");
            System.exit(1);
        }

        inputStream = System.in;

        Path path = Paths.get(args[0]);
        List<Item> items = readFile(path);

        Scanner scanner = new Scanner(Prior.inputStream);

        prioritise(items, scanner);
    }

    public static void prioritise(List<Item> items, Scanner scanner) {
        ArrayList<Item> newList = new ArrayList<>(items);

        for (int index = 0; index + 1 < items.size(); index++) {
            Item itemOne = items.get(index);

            for (int j = 1 + index; j < items.size(); j++) {
                Item itemTwo = items.get(j);

                if (2 == itemOne.prioritize(itemTwo, scanner)) {
                    newList.remove(j);
                    newList.remove(index);
                    newList.add(index, itemTwo);
                    newList.add(j, itemOne);
                }
            }
        }

        items.clear();
        items.addAll(newList);
    }

    private static List<Item> parseList (ListIndex<String> listIndex) {
        ArrayList<Item> arrayList = new ArrayList<>();

        while (listIndex.index < listIndex.list.size()) {
            Item item = new Item();
            item.parse(listIndex);
            arrayList.add(item);
        }

        return arrayList;
    }

    private static Item[] parseFile(List<String> text) {
        Iterator<String> iterator = text.iterator();
        ArrayList<Item> arrayList = new ArrayList<>();
        ListIndex<String> listIndex = new ListIndex<String>(text, 0);

        while (listIndex.index < listIndex.list.size()) {
            Item item = new Item();
            ListIndex<String> newListIndex = new ListIndex<>(listIndex);
            listIndex.index = listIndex.index + 1;
            item.parse(listIndex);
            arrayList.add(item);
        }

        return (Item[]) arrayList.toArray();
    }

    public static List<Item>  readFile (Path path) {
        try {
            List<String> list = Files.readAllLines(path);
            List<Item> items = convertToItems(list);
            return items;
        } catch (IOException e) {
            throw new RuntimeException("error reading file, " + path, e);
        }
    }

    public static List<Item> convertToItems(List<String> list) {
        ListIndex<String> listIndex = new ListIndex<>(list, 0);
        ArrayList<Item> arrayList = new ArrayList<>();

        while (listIndex.index < list.size()) {
            arrayList.add(parseItem(list, listIndex));
        }

        return arrayList;
    }

    public static Item parseItem(List<String> list, ListIndex<String> listIndex) {
        Item item = new Item();
        item.parse(listIndex);
        return item;
    }
}
