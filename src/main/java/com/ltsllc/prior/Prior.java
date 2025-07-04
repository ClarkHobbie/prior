package com.ltsllc.prior;

import com.ltsllc.commons.util.ImprovedPaths;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/******************************************************************************
 * Prioritize a list
 */
public class Prior {
    public static InputStream inputStream = System.in;
    private static int[] score = null;
    private static boolean[][] comparisons = null;

    public static void main(String[] args) {
        if (args.length <= 0) {
            System.out.println("usage prior <file>");
            System.exit(1);
        }

        //
         // make a backup
        //
        Path path = Paths.get(args[0]);
        Path backup = ImprovedPaths.appendToPath(path, ".backup");
        if (Files.exists(backup)) {
            try {
                Files.delete(backup);
            } catch (IOException e) {
                throw new RuntimeException("error deleting file, " + backup, e);
            }
        }
        try {
            Files.copy(path, backup);
        } catch (IOException e) {
            throw new RuntimeException("error making backup file, " + backup, e);
        }

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

        if (!backup.toFile().delete()) {
            throw  new RuntimeException("could not remove backup file, " + backup);
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
                    itemOne.setIsBefore(itemTwo);
                } else if (result == 2) {
                    itemTwo.incrementScore();
                    itemTwo.setIsBefore(itemOne);
                } else {
                    throw new RuntimeException("impossible case");
                }
            }
        }

        Comparator<Item> comparator = new ItemComparator();
        Collections.sort(items, comparator);
        items = items;
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

        for (int index = 0; index < list.size() && listIndex.index < list.size(); index++) {
            Item item = new Item();
            item.parse(listIndex);
            arrayList.add(item);
        }

        return arrayList;
    }

}
