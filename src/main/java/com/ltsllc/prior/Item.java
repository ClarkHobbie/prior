package com.ltsllc.prior;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Item {
    protected String string;

    public ArrayList<String> getReasons() {
        return reasons;
    }

    public void setReasons(ArrayList<String> reasons) {
        this.reasons = reasons;
    }

    protected ArrayList<String> reasons = new ArrayList<>();

    public String getItemName() {
        return string;
    }

    public void setItemName(String string) {
        this.string = string;
    }

    public Item () {
        this.string = null;
    }
    public Item (String string) {
        this.string = string;
    }

    public void addReason (String string) {
        reasons.add(string);
    }

    public void parse (ListIndex<String> listIndex) {
        string = listIndex.list.get(listIndex.index);
        listIndex.index++;
        for (int i = listIndex.index; i < listIndex.list.size(); i++) {
            if (listIndex.list.get(i).startsWith("\t") || listIndex.list.get(i).startsWith(" ")) {
                addReason(listIndex.list.get(listIndex.index));
            } else {
                listIndex.index = i;
                break;
            }
        }
    }

    public int prioritize (Item other) {
        int answer = 0;
        Scanner scanner = new Scanner(Prior.inputStream);

        while (answer != 1 && answer != 2) {
            System.out.println("Which is more important one or two?");
            System.out.print("1) ");
            System.out.print(string);
            System.out.println();
            System.out.print("2) ");
            System.out.println(other.string);
            answer = Integer.parseInt(scanner.nextLine());
        }

        return answer;
    }
}
