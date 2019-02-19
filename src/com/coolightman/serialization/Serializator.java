package com.coolightman.serialization;
//created by Coolightman
//19.02.2019 13:59

import javafx.scene.control.IndexedCell;

import java.io.*;

public class Serializator {
    public boolean serialization(Student s, String fileName) {
        boolean flag = false;
        File f = new File(fileName);
        try (ObjectOutputStream ostream = new ObjectOutputStream(new FileOutputStream(f))) {
            ostream.writeObject(s);
            flag = true;
        } catch (FileNotFoundException e) {
            System.err.println("Файл не может быть создан: "+ e);
        } catch (NotSerializableException e) {
            System.err.println("Класс не поддерживает сериализацию: "+ e);
        } catch (IOException e){
            System.err.println(e);
        }
        return flag;
    }

    public Student deserialization(String fileName) throws InvalidObjectException{
        File fr = new File(fileName);
        try (ObjectInputStream iStream = new ObjectInputStream(new FileInputStream(fr))) {
            Student st = (Student) iStream.readObject();
            return st;
        }catch (ClassNotFoundException ce){
            System.err.println("Класс не существует: "+ ce);
        }catch (FileNotFoundException e){
            System.err.println("Файл для десериализации не существует: "+ e);
        }catch (InvalidClassException ioe){
            System.err.println("Несовпадение версии классов: "+ ioe);
        }catch (IOException ioe){
            System.err.println(ioe);
        }
        throw new InvalidObjectException("Обьект не восстановлен");
    }
}
