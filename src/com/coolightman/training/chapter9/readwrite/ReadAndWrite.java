package com.coolightman.training.chapter9.readwrite;
//created by Coolightman
//18.02.2019 14:53

import java.io.*;
import java.util.Scanner;

public class ReadAndWrite {
    private static String sourceDir = "data\\";
    private static String fileName = "res.txt";
    private static String absoluteSourceDir = "D:\\Хлам\\Training\\data\\";

    public static void main(String[] args) {
        start();
    }

    private static void start() {
        readFromFileAndPrintInConsoleByChars(new File(absoluteSourceDir + "reading.txt"));
        writeInFile(new File(absoluteSourceDir + "wrote.txt"));
        readFromFileAndPrintInConsoleByChars(new File(absoluteSourceDir + "wrote.txt"));
        printInFile(new File(sourceDir, fileName));
        readFromFileByLines(new File(sourceDir + File.separator + fileName));
        readByScanner();
    }

    private static void writeInFile(File file) {
        String lineToWrite = "La-La-Lah-la!";
        try (FileWriter fw = new FileWriter(file)) {
            fw.write(lineToWrite);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readFromFileAndPrintInConsoleByChars(File file) {
        try (FileReader is = new FileReader(file)) {
            int b;
            while ((b = is.read()) != -1) {
                System.out.print((char) b);
            }
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printInFile(File file) {
        double[] v = {1.10, 1.2, 1.401, 5.01, 6.017, 7, 8};
        try (PrintWriter pw = new PrintWriter(
                            new BufferedWriter(
                            new FileWriter(file, true)))) {
            for (double version : v) {
                pw.printf("Java %.2g%n", version);
            }
        } catch (IOException e) {
            System.err.println("Ошибка открытия потока " + e);
        }
    }

    private static void readFromFileByLines(File file) {
        try (BufferedReader br = new BufferedReader(
                                new FileReader(file))) {
            String tmp = "";
            while ((tmp = br.readLine()) != null) {
                String[] s = tmp.split("\\n");
                for (String res : s) {
                    System.out.println(res);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readByScanner() {
        File f = new File(sourceDir, fileName);
        try {
            Scanner sc = new Scanner(f);
//            sc.useDelimiter("\\n");
            do {
//                String scanResult = sc.next();
                String scanResult = sc.nextLine();
                System.out.println(scanResult);
            } while (sc.hasNext());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
