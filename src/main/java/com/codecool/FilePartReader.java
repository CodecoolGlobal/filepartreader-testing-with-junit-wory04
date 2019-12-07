package com.codecool;

import java.io.*;

public class FilePartReader {
    private String filePath;
    private Integer fromLine;
    private Integer toLine;

    public FilePartReader() {
        filePath = "";
    }

    public void setup (String filePath, Integer fromLine, Integer toLine) throws IllegalArgumentException {
        if (fromLine < 1 || toLine < fromLine) {
            throw new IllegalArgumentException();
        }
        this.filePath = filePath;
        this.fromLine = fromLine;
        this.toLine = toLine;
    }

    public String read () throws IOException {
        FileInputStream in = new FileInputStream(filePath);
        String content = "";
        int i = 0;
        while ((i = in.read()) != -1) {
            content = content.concat(String.valueOf((char)i));
        }
        return content;
    }

    public String readLines () throws IOException {
        String result = "";
        String fileContent = read();
        BufferedReader reader = new BufferedReader(new StringReader(fileContent));
        String line = "";
        int counter = 1;
        while ((line = reader.readLine()) != null) {
            if (counter >= fromLine && counter <= toLine) {
                result = result.concat(line);
            }
            counter++;
        }
        return result;
    }
}
