package com.codecool;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FilePartReaderTest {
    private FilePartReader filePartReader;

    public FilePartReaderTest() {
        this.filePartReader = new FilePartReader();
    }

    @Test
    void testSetupForExceptionFromTooSmallFromLineArgument() {
        assertThrows(IllegalArgumentException.class, () -> {
            filePartReader.setup("src/main/resources/text.txt", -1, 1);
        });
    }

    @Test
    void testSetupForExceptionFromSmallerToLineArgument() {
        assertThrows(IllegalArgumentException.class, () -> {
            filePartReader.setup("src/main/resources/text.txt", 5, 3);
        });
    }

    @Test
    void testReadFirstLineCorrectness() {
        filePartReader.setup("src/main/resources/text.txt", 1, 1);
        String firstRow = "Characters, words, or lines:";
        try {
            String firstRowFromFile = filePartReader.readLines();
            assertEquals(firstRow, firstRowFromFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    @Test
    void testReadFileContent() {
        String content = "Characters, words, or lines:" +
                "The most familiar palindromes in English are character-unit palindromes. The characters read the same backward as forward. Some examples of palindromic words are redivider, deified, civic, radar, level, rotor, kayak, reviver, racecar, redder, madam, and refer.";
        try {
            String fileContent = filePartReader.read();
            assertEquals(content.substring(0, 150), fileContent.substring(0, 150));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void testIOExceptionHandledInReadLines() {
        filePartReader.setup("", 1, 1);
        assertThrows(IOException.class, () -> {
            filePartReader.readLines();
        });
    }

    @Test
    void testIOExceptionHandledInRead() {
        filePartReader.setup("", 1, 1);
        assertThrows(IOException.class, () -> {
            filePartReader.read();
        });
    }
}