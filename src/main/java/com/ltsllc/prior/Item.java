package com.ltsllc.prior;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Item {
    protected String itemName;

    public ArrayList<String> getReasons() {
        return reasons;
    }

    public void setReasons(ArrayList<String> reasons) {
        this.reasons = reasons;
    }

    protected ArrayList<String> reasons = new ArrayList<>();

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String string)
    {
        itemName = string;
    }

    public Item () {
        this.itemName = null;
    }
    public Item (String string) {
        this.itemName = string;
    }

    public void addReason (String string) {
        reasons.add(string);
    }

    public void parse (ListIndex<String> listIndex) {
        itemName = listIndex.list.get(listIndex.index);
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

    public int prioritize (Item other, Scanner scanner) {
        int answer = 0;

        while (answer != 1 && answer != 2) {
            System.out.println("Which is more important one or two?");
            System.out.print("1) ");
            System.out.print(itemName);
            System.out.println();
            System.out.print("2) ");
            System.out.println(other.itemName);

            answer = scanner.nextInt();
            String tempString = scanner.nextLine();

            if (answer == 1 && reasons.size() == 0) {
                newReason(scanner);
            } else if (answer == 1){
                setReason(scanner);
            }

            if (answer == 2 && other.getReasons().size() == 0) {
                other.newReason(scanner);
            } else if (answer == 2) {
                other.setReason(scanner);;
            }
        }

        return answer;
    }

    public void setReason(Scanner scanner) {
        int answer = -1;
        while (answer == -1) {
            if (reasons.size() < 1) {
                newReason(scanner);
            }
            else {
                System.out.println("Which reason?");
                System.out.println();
                printReasons();
                System.out.print(reasons.size() + 1);
                System.out.println(") New");

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

                if (answer > 0 && reasons.size() == 0) {
                    newReason(scanner);
                }
            }
        }
    }

    public void newReason (Scanner scanner) {
        System.out.println("Enter new reason");
        String tempString = scanner.nextLine();
        if (!tempString.equalsIgnoreCase("")) {
            reasons.add(tempString);
        }
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
