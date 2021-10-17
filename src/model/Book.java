package model;

import java.io.Serializable;

public class Book implements Serializable {
    private String name;
    private String code;
    private int number;

    @Override
    public String toString() {
        return "model.Book: " +
                "name= " + name + '\'' +
                ", code= " + code + '\'' +
                ", number= " + number;
    }

    public Book() {
    }

    public Book(String name, String code, int number) {
        this.name = name;
        this.code = code;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
