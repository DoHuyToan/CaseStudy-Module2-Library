package model;

import model.Book;

import java.io.Serializable;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class Card implements Serializable {
    private String code;
    private Book book;
    private Student student;
    private LocalDate borrowedDate;
    private LocalDate payDate;
    private LocalDate mustReturn;

    public Card(String code, Book book, Student student, LocalDate borrowedDate) {
        this.code = code;
        this.book = book;
        this.student = student;
        this.borrowedDate = borrowedDate;
        mustReturn = borrowedDate.plusDays(15);
    }

    @Override
    public String toString() {
        return "model.model.Card: " +
                "codeCard= " + code + '\'' +
                ", book= " + book +
                ", student= " + student +
                ", borrowedDate= " + borrowedDate +
                ", payDate= " + payDate +
                ", mustReturn= " + mustReturn;
    }

    public long getSumBorrowedDate(){
        long numday = DAYS.between(borrowedDate, payDate);
        return numday;
    }

    public Card() {
    }



    public Card(String code, Book book, Student student, LocalDate borrowedDate, LocalDate payDate) {
        this.code = code;
        this.book = book;
        this.student = student;
        this.borrowedDate = borrowedDate;
        this.payDate = payDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public LocalDate getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(LocalDate borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    public LocalDate getPayDate() {
        return payDate;
    }

    public void setPayDate(LocalDate payDate) {
        this.payDate = payDate;
    }
}
