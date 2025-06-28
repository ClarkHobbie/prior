package com.ltsllc.prior;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
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
                String tempString = listIndex.list.get(i);
                int index = 0;
                while (Character.isWhitespace(tempString.charAt(index))) {
                    index++;
                }
                addReason(tempString.substring(index));
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

            answer = scanner.nextInt();
            scanner.nextLine();

            if (answer ==  1 && reasons.size() > 0) {
                answer = -1;
                while (answer == -1) {
                    System.out.println("What is the reason?");
                    System.out.println();
                    printReasons();
                    System.out.print(reasons.size() + 1);
                    System.out.println(") New");
                    System.out.print (reasons.size() + 2);
                    System.out.println(") Use above");

                    String tempString;

                    try {
                        tempString = scanner.nextLine();
                        answer = Integer.parseInt(tempString);
                    } catch (NumberFormatException e) {
                        answer = -1;
                    }

                    if (answer < 0 || answer > reasons.size() + 1) {
                        answer = -1;
                    }

                    if (answer == reasons.size() + 1) {
                        System.out.println("Enter new reason");
                        tempString = scanner.nextLine();
                        reasons.add (tempString);
                    }
               }
            }
        }

        return answer;
    }

    public void printReasons() {
        if (reasons.size() > 0) {
            for (int number = 0; number < reasons.size(); number++) {
                System.out.print(number + 1);
                System.out.print(") ");
                System.out.println(reasons.get(number));
            }
        }
    }
}
