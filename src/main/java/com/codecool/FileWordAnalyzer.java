package com.codecool;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class FileWordAnalyzer {
    private FilePartReader filePartReader;

    public FileWordAnalyzer(FilePartReader filePartReader) {
        this.filePartReader = filePartReader;
    }

    public List getWordsOrderedAlphabetically() throws IOException {
        List<String> words = getWords();
        words.sort(new SortIgnoreCase());
        return words;
    }

    public List getWordsContainingSubstring(String substring) throws IOException {
        List<String> words = getWords();
        List<String> wordsContainsSubstring = new ArrayList<>();
        for (String word : words) {
            if (word.toLowerCase().contains(substring.toLowerCase())) {
                wordsContainsSubstring.add(word);
            }
        }
        return wordsContainsSubstring;
    }

    public List getStringsWhichPalindromes() throws IOException {
        List<String> words = getWords();
        List<String> palindromes = new ArrayList<>();

        for (String word : words) {

            String word_ = word.toLowerCase();
            StringBuilder toReverse_ = new StringBuilder();
            toReverse_.append(word_);
            String reversedWord_ = toReverse_.reverse().toString();

            if (reversedWord_.length() > 1 && reversedWord_.equals(word_)) {
                palindromes.add(word);
            }
        }
        return palindromes;
    }

    private List<String> getWords() throws IOException {
        List<String> words;
        String filePart = filePartReader.readLines().replaceAll("[^A-Za-z]", " ");
        words = Arrays.asList(filePart.split("\\s+"));
        Set<String> set = new HashSet<>(words);
        words = new ArrayList<>(set);
        return words;
    }

    public static class SortIgnoreCase implements Comparator<Object> {
        public int compare(Object o1, Object o2) {
            String s1 = (String) o1;
            String s2 = (String) o2;
            return s1.toLowerCase().compareTo(s2.toLowerCase());
        }
    }

}
