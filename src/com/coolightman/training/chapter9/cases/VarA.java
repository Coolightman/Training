package com.coolightman.training.chapter9.cases;
//created by Coolightman
//20.02.2019 14:56

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class VarA {
    private static String sourceDir = "data\\";
    private static String sourceFile = "textToWork.txt";

    public static void main(String[] args) {
        start();
    }

    private static void start() {
        String stringToDelFromFile = "к";
        String replaceableString = "Альф";
        String replacedString = "Адольф";
        String[] letters = {"а", "о", "и", "е", "э", "ы", "у", "ю", "я", "ё"};
        File textSource = new File(sourceDir, sourceFile);
        findAndDelete(textSource, stringToDelFromFile);
        findAndReplace(textSource, replaceableString, replacedString);
        findWordsByFirstLetter(textSource, letters);
        findWordsByLastAndFirstLetters(textSource);
        findTheMostLongWord(textSource);
    }


    //    Task_1 в каждой строке найти и удалить заданную подстроку
    private static void findAndDelete(File textSource, String stringToDelFromFile) {
        File fileForRes = new File(sourceDir, "task1Res.txt");
        ArrayList<String> text = scanTextFromFile(textSource);
        ArrayList<String> processedText = replacePart(text, stringToDelFromFile, "");
        writeTextInFile(fileForRes, processedText);
    }

    // Task_2 в каждой строке найти и заменить заданную подстроку на подстроку другой длинны
    private static void findAndReplace(File textSource, String replaceableString, String replacedString) {
        File fileForRes = new File(sourceDir, "task2Res.txt");
        ArrayList<String> text = scanTextFromFile(textSource);
        ArrayList<String> processedText = replacePart(text, replaceableString, replacedString);
        writeTextInFile(fileForRes, processedText);
    }

    //Task_3 найти слова начинающиеся с определнных букв
    private static void findWordsByFirstLetter(File textSource, String[] letters) {
        File fileForRes = new File(sourceDir, "task3Res.txt");
        ArrayList<String> text = scanTextFromFile(textSource);
        ArrayList<String> resultWords = findWordsByLetter(text, letters);
        writeTextInFile(fileForRes, resultWords);
    }

    //Task_4 найти слова для которых последняя буква совпадает с первой буквой следующего за ним
    private static void findWordsByLastAndFirstLetters(File textSource) {
        File fileForRes = new File(sourceDir, "task4Res.txt");
        ArrayList<String> text = scanTextFromFile(textSource);
        ArrayList<String> resultWords = findWordsByLastAndFirstLet(text);
        writeTextInFile(fileForRes, resultWords);
    }

    //Task_5 найти самое длинное слово
    private static void findTheMostLongWord(File textSource) {
        File fileForRes = new File(sourceDir, "task5Res.txt");
        ArrayList<String> text = scanTextFromFile(textSource);
        ArrayList<String> resultWords = findLongestWord(text);
        writeTextInFile(fileForRes, resultWords);
    }

    private static ArrayList<String> findLongestWord(ArrayList<String> text) {
        ArrayList<String> allWordsInText = new ArrayList<>();
        ArrayList<String> resultWord = new ArrayList<>();
        for (String line : text) {
            String[] allWordsInLine = line.split("[\\s,.!?():;\\-]+");
            Collections.addAll(allWordsInText, allWordsInLine);
        }

        int wordLength = 0;
        int wordNumber = -1;
        for (int i = 0; i < allWordsInText.size(); i++) {
            if (allWordsInText.get(i).length() > wordLength) {
                wordLength = allWordsInText.get(i).length();
                wordNumber = i;
            }
        }
        resultWord.add(allWordsInText.get(wordNumber));
        return resultWord;
    }

    private static ArrayList<String> findWordsByLastAndFirstLet(ArrayList<String> text) {
        ArrayList<String> resultWords = new ArrayList<>();
        for (String line : text) {
            String[] allWordsInLine = line.split("[\\s,.!?():;\\-]+");
            for (int i = 0; i < allWordsInLine.length - 1; i++) {
                char lastInWord = allWordsInLine[i].toLowerCase().charAt(allWordsInLine[i].length() - 1);
                char firstInWord = allWordsInLine[i + 1].toLowerCase().charAt(0);
                if (lastInWord == firstInWord) {
                    resultWords.add(allWordsInLine[i]);
                    resultWords.add(allWordsInLine[i + 1]);
                }
            }
        }
        return resultWords;
    }

    private static ArrayList<String> findWordsByLetter(ArrayList<String> text, String[] letters) {
        ArrayList<String> resultWords = new ArrayList<>();
        for (String line : text) {
            String[] allWordsInLine = line.split("[\\s,.!?():;\\-]+");
            for (String letter : letters) {
                for (String word : allWordsInLine) {
                    if (word.toLowerCase().startsWith(letter)) {
                        resultWords.add(word);
                    }
                }
            }
        }
        return resultWords;
    }

    private static void writeTextInFile(File fileForRes, ArrayList<String> processedText) {
        fileForRes.delete(); // что бы не добавлялось при перезапуске одно и то же
        for (String line : processedText) {
            try (FileWriter fw = new FileWriter(fileForRes, true)) {
                fw.write(line + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static ArrayList<String> replacePart(ArrayList<String> text, String stringToDelFromFile, String s) {
        ArrayList<String> processedText = new ArrayList<>();
        for (String line : text) {
            String processedLine = line.replace(stringToDelFromFile, s);
            processedText.add(processedLine);
        }
        return processedText;
    }

    static ArrayList<String> scanTextFromFile(File file) {
        ArrayList<String> text = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            do {
                String textLine = scanner.nextLine();
                text.add(textLine);
            } while (scanner.hasNextLine());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//        printTextConsole(text);
        return text;
    }

    private static void printTextConsole(ArrayList<String> text) {
        for (String line : text) {
            System.out.println(line);
        }
    }
}
