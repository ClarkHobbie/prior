package com.ltsllc.prior;

import java.util.ArrayList;

/**
 * A class for building byte arrays.
 */
public class ByteArrayBuilder {
    protected ArrayList<byte[]> list = new ArrayList<>();

    public ByteArrayBuilder () {
    }

    public void add(String string) {
        list.add(string.getBytes());
    }

    /**
     * Create a byte array from what you have.
     *
     * @return The corresponding byte array.
     */
    public byte[] toByteArray() {
        int size = 0;

        for (int index = 0; index < list.size(); index++) {
            size += list.get(index).length;
        }

        byte[] buff = new byte[size];
        int buffIndex = 0;
        for (int index = 0; index < list.size(); index++) {
            byte[] array = list.get(index);
            for (int arrayIndex = 0; arrayIndex < array.length; arrayIndex++) {
                buff[buffIndex] = array[arrayIndex];
                buffIndex++;
            }
        }

        return buff;
    }

    /**
     * Add an array of strings to the instance.
     *
     * This method will add each string to the instance, not the array itself.
     *
     * @param strings The array of strings to add.
     */
    public void add(String[] strings) {
        for (String string : strings) {
            add(string);
        }
    }
}
