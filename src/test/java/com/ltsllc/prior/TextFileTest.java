package com.ltsllc.prior;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class TextFileTest {

    @Test
    void write() {
        String[] text = {
                "one",
                "\tbecause it's number one",
                "two"
        };

        File file = new File("temp.txt");

        TextFile textFile = new TextFile(file);
        textFile.setText(text);
        textFile.write();

        assert (file.exists());
    }

    @Test
    void delete() {
        File file = new File("temp.txt");
        TextFile textFile = new TextFile(file);
        textFile.delete();
        assert (!file.exists());

    }

    @Test
    void setText() {
    }

    @Test
    void load() {
    }
}