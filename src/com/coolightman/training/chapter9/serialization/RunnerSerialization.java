package com.coolightman.training.chapter9.serialization;
//created by Coolightman
//19.02.2019 14:26

import java.io.InvalidObjectException;

public class RunnerSerialization {
    public static void main(String[] args) {
        Student ob = new Student(
                "CTaT","Alexey", 1, "G09i1");
        System.out.println(ob);
        String file = "data\\demo.data";
        Serializator sz = new Serializator();
        boolean b = sz.serialization(ob, file);
        Student.faculty = "GEO";
        Student res = null;
        try {
            res = sz.deserialization(file);
        } catch (InvalidObjectException e) {
            e.printStackTrace();
        }
        System.out.println(res);
    }
}
