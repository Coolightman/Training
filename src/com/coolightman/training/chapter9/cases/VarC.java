package com.coolightman.training.chapter9.cases;
//created by Coolightman
//21.02.2019 12:17

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import static com.coolightman.training.chapter9.cases.VarA.replacePart;
import static com.coolightman.training.chapter9.cases.VarA.scanTextFromFile;

public class VarC {
    private static String sourceDir = "data\\";
    private static String sourceFile = "taskResult.txt";

    public static void main(String[] args) {
        start();
    }

    private static void start() {
        createSortRndNumbersFile();
        findAndReplace();
        reverseAllSymbolsInLine();
        toUpperCaseWordsLongerThanTwoSymbols();
    }

    private static void createSortRndNumbersFile() {
        int taskNumber = 1;
        String taskDir = createTaskDir(taskNumber);
        File taskResult = new File(taskDir, sourceFile);
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            numbers.add((int) (Math.random() * 100));
        }
        Collections.sort(numbers);
        writeNumbersInFile(taskResult, numbers);
    }

    private static void findAndReplace() {
        int taskNumber = 2;
        String taskDir = createTaskDir(taskNumber);

        File textSource = new File("src\\com\\coolightman\\training\\chapter9\\readwrite\\ReadAndWrite.java");
        File taskResult = new File(taskDir, sourceFile);
        String replaceableString = "public";
        String replacedString = "private";

        ArrayList<String> text = scanTextFromFile(textSource);
        ArrayList<String> processedText = replacePart(text, replaceableString, replacedString);
        writeTextInFile(taskResult, processedText);
    }

    private static void reverseAllSymbolsInLine() {
        int taskNumber = 3;
        String taskDir = createTaskDir(taskNumber);

        File textSource = new File("src\\com\\coolightman\\training\\chapter9\\readwrite\\ReadAndWrite.java");
        File taskResult = new File(taskDir, sourceFile);

        ArrayList<String> text = scanTextFromFile(textSource);
        ArrayList<String> processedText = reverseLines(text);
        writeTextInFile(taskResult, processedText);
    }

    private static void toUpperCaseWordsLongerThanTwoSymbols() {
        int taskNumber = 4;
        String taskDir = createTaskDir(taskNumber);
        File textSource = new File("src\\com\\coolightman\\training\\chapter9\\readwrite\\ReadAndWrite.java");
        File taskResult = new File(taskDir, sourceFile);

        ArrayList<String> text = scanTextFromFile(textSource);
        ArrayList<String> processedText = toUpperCaseWords(text);
        writeTextInFile(taskResult, processedText);
    }

    private static ArrayList<String> toUpperCaseWords(ArrayList<String> text) {
        ArrayList<String> processedText = new ArrayList<>();
        ArrayList<String> partsBuffer = new ArrayList<>();

        for (String line : text) {
            String[] parts = line.split("[\\W]");
            for (String part :parts) {
                if (part.length()>2){
                    partsBuffer.add(part);
                }
            }
            for (String word: partsBuffer){
                String newWord = word.toUpperCase();
                line = line.replace(word, newWord);
            }
            processedText.add(line);
        }
        return processedText;
    }

    private static ArrayList<String> reverseLines(ArrayList<String> text) {
        ArrayList<String> processedText = new ArrayList<>();
        for (String line : text) {
            StringBuilder stringBuilder = new StringBuilder(line);
            processedText.add(stringBuilder.reverse().toString());
        }
        return processedText;
    }

    private static String createTaskDir(int taskNumber) {
        String taskDir = sourceDir + "taskC_" + taskNumber + File.separator;
        File taskDirectory = new File(taskDir);
        if (!taskDirectory.exists()) {
            taskDirectory.mkdir();
        }
        return taskDir;
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

    private static void writeNumbersInFile(File fileForRes, ArrayList<Integer> numbers) {
        fileForRes.delete(); // что бы не добавлялось при перезапуске одно и то же
        for (Integer number : numbers) {
            try (FileWriter fw = new FileWriter(fileForRes, true)) {
                fw.write(number + " ");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
