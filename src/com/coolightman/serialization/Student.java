package com.coolightman.serialization;
//created by Coolightman
//19.02.2019 13:50

import java.io.Serializable;

public class Student implements Serializable {
    static String faculty;
    private String name;
    private int id;
    private transient String password;
    private static final long serialVersionUID = 1L;

    Student(String nameOfFacility, String name, int id, String password){
        faculty = nameOfFacility;
        this.name = name;
        this.id = id;
        this.password = password;
    }

    public String toString(){
        return "\nfaculty "+faculty+"\nname "+name+"\nID "+id+"\npassword "+password;
    }
}
