package com.ltsllc.prior;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class Prior {
    public static InputStream inputStream = System.in;
    private static int[] score = null;
    private static boolean[][] comparisons = null;

    public static void main(String[] args) {
        if (args.length <= 0) {
            System.out.println("usage prior <file>");
            System.exit(1);
        }

        Path path = Paths.get(args[0]);
        List<Item> items = readFile(path);

        score = new int[items.size()];
        Arrays.fill(score, 0);

        comparisons = new boolean[items.size()][items.size()];
        for (int index = 0; index < items.size(); index++) {
            for (int j = 0; j < items.size(); j++) {
                comparisons[index][j] = false;
            }
        }

        Scanner scanner = new Scanner(Prior.inputStream);
        prioritise(items, scanner);

        path.toFile().delete();

        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(path.toFile());
        } catch (IOException e) {
            throw new RuntimeException("could not open " + path.toFile(), e);
        }

        for (Item item : items) {
            item.write(fileWriter);
        }

        try {
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("could not close file " + path.toFile(), e);
        }
    }

    public static void prioritise(List<Item> items, Scanner scanner) {
        for (int firstIndex = 0; firstIndex < items.size(); firstIndex++) {
            Item itemOne = items.get(firstIndex);

            for (int secondIndex = 1 + firstIndex; secondIndex < items.size(); secondIndex++) {
                Item itemTwo = items.get(secondIndex);

                int result = itemOne.compareTo(itemTwo, scanner);
                if (result == 1) {
                    itemOne.incrementScore();
                    itemOne.setIsBefore(itemTwo, true);
                } else if (result == 2) {
                    itemTwo.incrementScore();
                    itemTwo.setIsBefore(itemOne, true);
                } else {
                    throw new RuntimeException("impossible case");
                }
            }
        }

        Comparator<Item> comparator = new ItemComparator();
        Collections.sort(items, comparator);
        items = items;
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
