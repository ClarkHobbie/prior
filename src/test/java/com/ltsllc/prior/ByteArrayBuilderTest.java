package com.ltsllc.prior;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ByteArrayBuilderTest {

    @Test
    void add() {
        ByteArrayBuilder byteArrayBuilder = new ByteArrayBuilder();

        String string = "hi there";
        byteArrayBuilder.add(string);
        byte[] buff = byteArrayBuilder.toByteArray();

        assert (buff != null);
        assert (bytesEqual(buff, string.getBytes()));
    }

    boolean bytesEqual(byte[] arrayOne, byte[] arrayTwo) {
        if (arrayOne == null || arrayTwo == null || arrayOne.length < 0 || arrayTwo.length < 0 || arrayOne.length != arrayTwo.length)
        {
            return false;
        }

        for (int index = 0; index < arrayOne.length; index++) {
            if (arrayOne[index] != arrayTwo[index]) {
                return false;
            }
        }

        return true;
    }

    @Test
    void toByteArray() {
        String string = "one";
        byte[] buff = { 'o', 'n', 'e'};

        ByteArrayBuilder byteArrayBuilder = new ByteArrayBuilder();
        byteArrayBuilder.add(string);
        byte[] byteArray = byteArrayBuilder.toByteArray();

        assert(bytesEqual(buff, byteArray));
    }
}