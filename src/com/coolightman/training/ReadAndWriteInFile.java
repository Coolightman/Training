package com.coolightman.training;
//created by Coolightman
//18.02.2019 14:53

import java.io.*;

public class ReadAndWriteInFile {
    private static String dataDirectory = "D:\\Хлам\\Training\\data\\";

    public static void main(String[] args) {
        start();
    }

    private static void start() {
        readFromFile(new File(dataDirectory+"reading.txt"));
        writeInFile();
        readFromFile(new File(dataDirectory+"wrote.txt"));
    }

    private static void writeInFile() {
        String linetoWrite = "La-La-Lah-la!";
        try(FileWriter fw = new FileWriter(dataDirectory+"wrote.txt")) {
            fw.write(linetoWrite);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readFromFile(File file) {
        try (FileReader is = new FileReader(file)) {
            int b = 0;
            while ((b = is.read()) != -1) {
                System.out.print((char) b);
            }
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
