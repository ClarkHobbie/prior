package com.ltsllc.prior;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        File file = new File(args[0]);
        Path path = ImprovedPaths.toPath(file);
        List<String> text = readFile(path);



    }

    public static List<String>  readFile (Path path) {
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException("error reading file, " + path, e);
        }
    }
}
