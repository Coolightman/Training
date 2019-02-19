package com.coolightman.training;
//created by Coolightman
//19.02.2019 9:12

import java.io.*;

public class DemoWriter {
    private static String sourceDirectory = "data\\";
    private static String fileName = "res.txt";

    public static void main(String[] args) {
        writeInRes();
        readFromRes();
    }

    private static void writeInRes() {
        File f = new File(sourceDirectory, fileName);
        double[] v = {1.10, 1.2, 1.401, 5.01, 6.017, 7, 8};
        try (PrintWriter pw = new PrintWriter(
                              new BufferedWriter(
                              new FileWriter(f, true)))) {
            for (double version : v) {
                pw.printf("Java %.2g%n", version);
            }
        } catch (IOException e) {
            System.err.println("Ошибка открытия потока " + e);
        }
    }

    private static void readFromRes() {
        try (BufferedReader br = new BufferedReader(
                                new FileReader(sourceDirectory + File.separator + fileName))) {
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
}
