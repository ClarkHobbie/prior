package com.ltsllc.prior;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImprovedPaths {
    public static Path toPath(File file) {
        String pathString = file.getAbsolutePath();
        Path path = Paths.get(pathString);
        return path;
    }

    public static Path appendToPath(Path path, String suffix) {
        String string = path + suffix;
        Path tempPath = Path.of (string);
        return tempPath;
    }
}
