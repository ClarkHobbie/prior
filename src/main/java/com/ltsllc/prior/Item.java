package com.ltsllc.prior;

import java.io.*;
import java.util.*;

public class Item {
    protected String itemName;
    protected int score = 0;
    protected Map<Item, Boolean> comesBefore = new HashMap<>();

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int incrementScore () {
        score++;
        return score;
    }

    public Map<Item, Boolean> getComesBefore() {
        return comesBefore;
    }

    public void setComesBefore(Map<Item, Boolean> comesBefore) {
        this.comesBefore = comesBefore;
    }

    public boolean isBefore (Item item) {
        Boolean bool = false;

        bool = comesBefore.get(item);
        if (bool == null) {
            return false;
        }

        return bool.booleanValue();
    }


    public void setIsBefore (Item item, boolean value) {
        Boolean bool = new Boolean(value);
        comesBefore.put(item, bool);
    }

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
                System.out.println("Enter new reason or hit return to choose an existing reason");
                System.out.println();
                printReasons();

                String tempString = null;

                tempString = scanner.nextLine();
                if (tempString.equalsIgnoreCase("")) {
                    break;
                }
                tempString.trim();
                reasons.add(tempString);
            }
        }
    }

    public void newReason (Scanner scanner) {
        System.out.println("Enter new reason or hit return for none");
        String tempString = scanner.nextLine();
        if (!tempString.equalsIgnoreCase("")) {
            reasons.add(tempString);
        }
    }

    public void printReasons() {
        if (reasons.size() > 0) {
            for (String string : reasons) {
                System.out.println(string);
            }
        }
    }

    public void write(FileWriter fileWriter) {
        try {
            fileWriter.write(itemName + "\n");
        } catch (IOException e) {
            throw new RuntimeException("could not write item name to file", e);
        }

        for (String reason : reasons) {
            try {
                fileWriter.write("\t" + reason + "\n");
            } catch (IOException e) {
                throw new RuntimeException("could not write reason, " + reason + " to file", e);
            }
        }
    }

    public int compareTo(Item item, Scanner scanner) {
        return prioritize(item, scanner);
    }
}
