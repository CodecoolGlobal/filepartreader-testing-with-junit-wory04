package com.codecool;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileWordAnalyzerTest {
    FilePartReader filePartReader;
    FileWordAnalyzer fileWordAnalyzer;

    public FileWordAnalyzerTest() {
        this.filePartReader = new FilePartReader();
        filePartReader.setup("src/main/resources/text.txt", 1,25);
    }

    @BeforeEach
    void setUp() {
        this.fileWordAnalyzer = new FileWordAnalyzer(filePartReader);
    }


    @Test
    void getWordsOrderedAlphabetically() {
        List<String> testWords = Arrays.asList("A", "a", "ABBA", "accepted", "actor", "Ages", "Alomomola");
        try {
            List wordsFromFile = fileWordAnalyzer.getWordsOrderedAlphabetically();
            assertEquals(testWords.get(6), wordsFromFile.get(6));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void testWordsContainingSubstring() {
        String sub = "ame";
        try {
            List wordsContainingSubstring = fileWordAnalyzer.getWordsContainingSubstring(sub);
            assertEquals(wordsContainingSubstring.size(), 6);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    @Test
    void testStringsWhichPalindromes() {
        try {
            List palindromes = fileWordAnalyzer.getStringsWhichPalindromes();
            assertEquals(palindromes.size(), 28);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}