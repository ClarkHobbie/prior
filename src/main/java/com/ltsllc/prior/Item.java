package com.ltsllc.prior;

import java.io.*;
import java.util.*;

/******************************************************************************
 * An item to prioritize.
 */
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

    public boolean isBefore (Item item) {
        Boolean bool = false;

        bool = comesBefore.get(item);
        if (bool == null) {
            return false;
        }

        return bool.booleanValue();
    }


    public void setIsBefore (Item item) {
        Boolean value = new Boolean(true);
        comesBefore.put(item, value);
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

    /**************************************************************************
     * Parse an {@link Item} from a {@link ListIndex}.
     *
     * The method updates the listIndex.index to reflect where parsing
     * finished.
     *
     * @param listIndex Where parsing should take place.
     */
    public void parse (ListIndex<String> listIndex) {
        if (listIndex.index > listIndex.list.size()) {
            throw new RuntimeException("impossible case");
        }
        if (Character.isWhitespace(listIndex.list.get(0).charAt(0))) {
            throw new RuntimeException("impossible case");
        }

        itemName = listIndex.list.get(listIndex.index);
        listIndex.index++;

        for (int index = listIndex.index; index < listIndex.list.size() && Character.isWhitespace(listIndex.list.get(listIndex.index).charAt(0));
        listIndex.index++) {
            String string = listIndex.list.get(index);
            string = string.trim();
            reasons.add(string);
        }
    }

    /**************************************************************************
     * Compare one {@link Item} with another and return 1 if the instance is
     * more important than the other instance and 2 if the other instance is
     * more important than this instance.
     *
     * The method uses the provided {@link Scanner} to get answers and
     * {@link System#out} to print to the user.
     *
     * @param other The other {@link Item} to compare to.
     * @param scanner The source for answers as to which instance is more
     *                important.
     * @return 1 if this instance is more important than the other instance,
     * 2 if the other is more important.
     */
    public int prioritize (Item other, Scanner scanner) {
        int answer = 0;

        while (answer != 1 && answer != 2) {
            System.out.println("Which is more important?");
            System.out.print("1) ");
            System.out.print(itemName);
            System.out.println();
            System.out.print("2) ");
            System.out.println(other.itemName);

            answer = scanner.nextInt();
            scanner.nextLine();

            if (answer == 1) {
                addReason(scanner);
            } else if (answer == 2) {
                other.addReason(scanner);
            }
        }

        return answer;
    }

    /**************************************************************************
     * Ask for a reason why an instance is more important.
     *
     * This method will print the existing reasons (if any) on
     * {@link System#out}.  If the response is the empty string (""), then the
     * method assumes that the user wants to use an existing reason or no
     * reason at all, and doesn't add a reason.
     *
     * @param scanner The object to be used to get the answer.
     */
    public void addReason(Scanner scanner) {
        String string = null;
        while (string == null || string.length() == 0) {
            System.out.println("Is there a reason? (hit return for an existing reason or no reason) ");
            System.out.println();
            printReasons();
            string = scanner.nextLine();
            if (string.equalsIgnoreCase("")) {
                break;
            } else {
                string.trim();
                reasons.add(string);
            }
        }
    }

    public void printReasons() {
        if (reasons.size() > 0) {
            for (String string : reasons) {
                System.out.println(string);
            }
        }
    }

    /**************************************************************************
     * Write the {@link Item} out to the provided {@link FileWriter}.
     *
     * @param fileWriter The {@link FileWriter} to write to.
     */
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
