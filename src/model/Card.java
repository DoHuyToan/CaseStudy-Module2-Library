package model;

import java.io.Serializable;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class Card implements Serializable {
    public static final int MAXDATE = 30;
    public static final int MEDIUMDATE = 15;
    public static final int MINDATE = 0;
    public static final int MAXMONEY = 500;
    public static final int MEDIUMMONEY = 200;
    public static final int MINMONEY = 100;
    private String code;
    private Book book;
    private Student student;
    private LocalDate borrowedDate;
    private LocalDate payDate;

    public LocalDate mustReturn(){
        LocalDate mustReturn = borrowedDate.plusDays(15);
        return mustReturn;
    }

    public double fineMoney (){                       //quá hạn trả Sách bị phạt tiền
        LocalDate today = LocalDate.now();
        double fineMoney = 0;
        if(payDate == null){
            if(DAYS.between(mustReturn(), today) > MAXDATE){
                fineMoney = MAXMONEY;
            } else{
                if(DAYS.between(mustReturn(), today) > MEDIUMDATE){
                    fineMoney = MEDIUMMONEY;
                }
                else{
                    if (DAYS.between(mustReturn(), today) > MINDATE){
                        fineMoney = MINMONEY;
                    }
                }
            }
        }
        else  if (payDate.isAfter(mustReturn())){
            if(DAYS.between(mustReturn(), payDate) > MAXDATE){
                fineMoney = MAXMONEY;
            }
            else{
                if(DAYS.between(mustReturn(), payDate) > MEDIUMDATE){
                    fineMoney = MEDIUMMONEY;
                }
                else{
                    if (DAYS.between(mustReturn(), payDate) > MINDATE){
                        fineMoney = MINMONEY;
                    }
                }
            }
        }
        return fineMoney;
    }



    @Override
    public String toString() {
        return "Card: " +
                "codeCard= " + code + '\'' +
                ", student= " + student +
                ", book= " + book +
                ", borrowedDate= " + borrowedDate +
                ", payDate= " + payDate +
                ", mustReturn= " + mustReturn() +
                ", fineMoney= " + fineMoney();
    }

    public Card() {
    }

    public Card(String code, Student student, Book book, LocalDate borrowedDate) {
        this.code = code;
        this.book = book;
        this.student = student;
        this.borrowedDate = borrowedDate;
    }

    public Card(String code, Student student, LocalDate borrowedDate) {
        this.code = code;
        this.student = student;
        this.borrowedDate = borrowedDate;
    }


    public Card(String code, Student student) {
        this.code = code;
        this.student = student;
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
