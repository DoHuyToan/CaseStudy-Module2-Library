package model;

import java.io.Serializable;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class Card implements Serializable {
    public static final int MAXDATE = 45;
    public static final int MEDIUMDATE = 30;
    public static final int MINDATE = 15;
    public static final int MAXMONEY = 500;
    public static final int MEDIUMMONEY = 200;
    public static final int MINMONEY = 100;
    private String code;
    private Book book;
    private Student student;
    private LocalDate borrowedDate;
    private LocalDate payDate;
    private LocalDate mustReturn;

    public double fineMoney (){                       //quá hạn trả Sách bị phạt tiền
        LocalDate today = LocalDate.now();
        double fineMoney = 0;
        if(payDate == null || payDate.isAfter(mustReturn)){
            if(DAYS.between(borrowedDate, today) > MAXDATE){
                fineMoney = MAXMONEY;
            }
            else{
                if(DAYS.between(borrowedDate, today) > MEDIUMDATE){
                    fineMoney = MEDIUMMONEY;
                }
                else{
                    if (DAYS.between(borrowedDate, today) > MINDATE){
                        fineMoney = MINMONEY;
                    }
                }
            }
        }
        return fineMoney;
    }

    public Card(String code, Book book, Student student, LocalDate borrowedDate) {
        this.code = code;
        this.book = book;
        this.student = student;
        this.borrowedDate = borrowedDate;
        mustReturn = borrowedDate.plusDays(15);
    }

    public Card(String code, Student student) {
        this.code = code;
        this.student = student;
    }

    @Override
    public String toString() {
        return "Card: " +
                "codeCard= " + code + '\'' +
                ", student= " + student.getName() +
                ", bookName= " + book.getName() +
                ", bookNumber= " + book.getNumber() +
                ", borrowedDate= " + borrowedDate +
                ", payDate= " + payDate +
                ", mustReturn= " + mustReturn +
                ", fineMoney= " + fineMoney();
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
