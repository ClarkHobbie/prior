package com.ltsllc.prior;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TextFile {
    protected Path path;
    protected List<String> text;

    public List<String> getText() {
        return text;
    }

    public void setText(List<String> text) {
        this.text = text;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public void setPath (File file) {
        Path path = ImprovedPaths.toPath(file);
        setPath(path);
    }

    public TextFile(File file) {
        try {
            text = Files.readAllLines(ImprovedPaths.toPath(file));
        } catch (IOException e) {
            throw new RuntimeException("error reading file, " + file, e);
        }
    }

    public void setText (String[] newText) {
        ArrayList<String> arrayList = new ArrayList<>();

        for (int index = 0; index < newText.length; index++) {
            arrayList.add(newText[index]);
        }
    }

    public TextFile(Path path) {
        try {
            text = Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException("error reading file, " + path, e);
        }
    }
}
