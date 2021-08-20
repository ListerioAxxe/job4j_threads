package ru.job4j;

import java.io.*;
import java.util.function.Predicate;

public class ParseFile {
    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    public String getContent(Predicate<Character> predicate) throws IOException {
        try (var in = new BufferedReader(new FileReader(file))) {
            var buf = new StringBuilder();
            int data;
            while ((data = in.read()) > 0) {
                if (predicate.test((char) data)) {
                    buf.append(data);
                }
            }
            return buf.toString();
        }
    }
}
