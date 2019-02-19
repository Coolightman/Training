package com.coolightman.training;
//created by Coolightman
//18.02.2019 16:40

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class workWithFile {

    public static void main(String[] args) {
        workWithFile();
        workWithDir();
    }

    private static void workWithFile() {
        String dataDirectory = "D:\\Хлам\\Training\\data\\";
        File fp = new File(dataDirectory + "testFileSystem.txt");
        if (fp.exists()) {
            System.out.println(fp.getName() + " существует");
            System.out.println("Путь к файлу: \t" + fp.getPath());
            System.out.println("Абсолютный путь: \t" + fp.getAbsolutePath());
            System.out.println("Размер файла: \t" + fp.length());
            System.out.println("Последняя модификация: \t" + new Date(fp.lastModified()));
            System.out.println("Файл доступен для чтения: \t" + fp.canRead());
            System.out.println("Файл доступен для записи: \t" + fp.canWrite());
            System.out.println("Файл удален: \t" + fp.delete());

        } else {
            try {
                if (fp.createNewFile()) {
                    System.out.println("Файл " + fp.getName() + " создан");
                }
            } catch (IOException e) {
                System.err.println(e);
            }
        }

    }

    private static void workWithDir() {
        File dir = new File("D:\\Хлам\\Training\\data\\");
        if (dir.exists() && dir.isDirectory()) {
            System.out.println("каталог " + dir.getName() + " существует");
        }

        File[] files = dir.listFiles();
        assert files != null;
        for (File file : files) {
            Date date = new Date(file.lastModified());
            System.out.println("\n" + file.getPath() + " \t|" + file.length() + " \t|" + date);
        }
        File root = File.listRoots()[1];
        System.out.printf("\n%s %,d свободно.", root.getPath(), root.getUsableSpace());
    }

}
