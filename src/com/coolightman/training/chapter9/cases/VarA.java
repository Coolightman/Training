package com.coolightman.training.chapter9.cases;
//created by Coolightman
//20.02.2019 14:56

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class VarA {
    private static String sourceDir = "data\\";

    public static void main(String[] args) {
        start();
    }

    private static void start() {
        String stringToDelFromFile = "нн";
        String replaceableString = "Альф";
        String replacedString = "Ральф";
        File textSource = new File(sourceDir, "textToWork.txt");
        findAndDelete(textSource, stringToDelFromFile);
        findAndReplace(textSource, replaceableString, replacedString);
    }

    //    Task_1
    private static void findAndDelete(File file, String stringToDelFromFile) {
        File fileForRes = new File(sourceDir, "task1Res.txt");
        ArrayList<String> text = scanTextFromFile(file);
        ArrayList<String> processedText = replacePart(text, stringToDelFromFile, "");
        writeTextInFile(fileForRes, processedText);
    }

    // Task_2
    private static void findAndReplace(File file, String replaceableString, String replacedString) {
        File fileForRes = new File(sourceDir, "task2Res.txt");
        ArrayList<String> text = scanTextFromFile(file);
        ArrayList<String> processedText = replacePart(text, replaceableString, replacedString);
        writeTextInFile(fileForRes, processedText);
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

    private static ArrayList<String> replacePart(ArrayList<String> text, String stringToDelFromFile, String s) {
        ArrayList<String> processedText = new ArrayList<>();
        for (String line : text) {
            String processedLine = line.replace(stringToDelFromFile, s);
            processedText.add(processedLine);
        }
        return processedText;
    }

    private static ArrayList<String> scanTextFromFile(File file) {
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
