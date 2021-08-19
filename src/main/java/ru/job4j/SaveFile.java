package ru.job4j;

import java.io.*;

public class SaveFile {
    private final File file;

    public SaveFile(File file) {
        this.file = file;
    }

    public void saveContent(String content) throws IOException {
        try (var out = new BufferedWriter(new FileWriter(file))) {
            out.write(content);
        }
    }
}
