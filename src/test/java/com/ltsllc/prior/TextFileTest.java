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
        String[] text = {
                "one",
                "two"
        };
        TextFile textFile = new TextFile(new File("temp.txt"));
        textFile.setText(text);

        assert (textFile.getText().get(0).equalsIgnoreCase("one"));
    }

    @Test
    void load() {
        TextFile textFile = new TextFile("temp.txt");
        String[] text = {
                "one",
                "two"
        };
        textFile.setText(text);
        textFile.write();

        textFile = new TextFile("temp.txt");
        textFile.load();

        assert (textFile.getText().get(0).equalsIgnoreCase("one"));
    }
}