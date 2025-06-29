package com.ltsllc.prior;

import java.util.ArrayList;

public class ByteArrayBuilder {
    protected ArrayList<byte[]> list = new ArrayList<>();

    public ByteArrayBuilder () {
    }

    public void add(String string) {
        list.add(string.getBytes());
    }

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

    public void add(String[] strings) {
        for (String string : strings) {
            add(string);
        }
    }
}
