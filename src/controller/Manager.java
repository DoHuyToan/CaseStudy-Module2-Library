package controller;

import model.Book;
import model.Card;
import model.Student;

import java.time.LocalDate;
import java.util.ArrayList;

public class Manager {
    ArrayList<Book> bookArrayList = new ArrayList<>();
    ArrayList<Student> studentArrayList = new ArrayList<>();
    ArrayList<Card> cardArrayList = new ArrayList<>();

    public ArrayList<Card> findCardNeedPay(){                   //tìm danh sách Card có sách "Quá hạn trả" mà "Hiện tại Chưa trả"
        LocalDate today = LocalDate.now();
        ArrayList<Card> cardArrayList1 = new ArrayList<>();
        Card card = null;
        for (int i=0; i<cardArrayList.size(); i++){
            if(cardArrayList.get(i).getPayDate() == null
                    && cardArrayList.get(i).getBorrowedDate().plusDays(15).isBefore(today)){
                cardArrayList1.add(cardArrayList.get(i));
            }
        }
        return cardArrayList1;
    }

    public ArrayList<Card> findCardNeedPayForMonth(){                   //tìm danh sách Card có sách "Quá hạn trả" mà "Cuối mỗi tháng Chưa trả"
        ArrayList<Card> cardArrayList1 = new ArrayList<>();
        Card card = null;
        for (int i=0; i<cardArrayList.size(); i++){
            LocalDate dateOfMonth = cardArrayList.get(i).getBorrowedDate();
            LocalDate dateOfLastMonth = dateOfMonth.withDayOfMonth(dateOfMonth.lengthOfMonth());
            if(cardArrayList.get(i).getPayDate() == null
                    && cardArrayList.get(i).getBorrowedDate().plusDays(15).isBefore(dateOfLastMonth)){
                cardArrayList1.add(cardArrayList.get(i));
            }
        }
        return cardArrayList1;
    }

    public Book findBookByName(String name){             //tìm thông tin Sách theo tên
        Book book = null;
        for (int i=0; i<bookArrayList.size(); i++){
            if(bookArrayList.get(i).getName().equals(name)){
                book = bookArrayList.get(i);
            }
        }
        return book;
    }

    public Book findBookByCode(String code){             //tìm thông tin Sách theo code
        Book book = null;
        for (int i=0; i<bookArrayList.size(); i++){
            if(bookArrayList.get(i).getCode().equals(code)){
                book = bookArrayList.get(i);
            }
        }
        return book;
    }

    public void addBook(Book book){                  //thêm Sách
        bookArrayList.add(book);
    }

    public void showBook(){                               //hiển thị danh sách Sách
        for (Book b:bookArrayList) {
            System.out.println(b);
        }
    }

    public void editBook(String code, Book book){           //sửa Sách
        for (int i=0; i<bookArrayList.size(); i++){
            if(bookArrayList.get(i).getCode().equals(code)){
                bookArrayList.set(i, book);
            }
        }
    }

    public void removeBook(String code){                    //xóa Sách
        for (int i=0; i<bookArrayList.size(); i++){
            if(bookArrayList.get(i).getCode().equals(code)){
                bookArrayList.remove(i);
            }
        }
    }

    public void showStudent(){                           //hiển thị danh sách Sinh viên
        for (Student t:studentArrayList) {
            System.out.println(t);
        }
    }

    public void addStudent(Student student){             //thêm Sinh viên
        studentArrayList.add(student);
    }

    public void editStudent(String code, Student student){                  //sửa Sinh viên
        for (int i=0; i<studentArrayList.size(); i++){
            if(studentArrayList.get(i).getCode().equals(code)){
                studentArrayList.set(i, student);
            }
        }
    }

    public Student findStudentByName(String name){                 //tìm thông tin Sinh viên theo tên
        Student student = null;
        for (int i=0; i<studentArrayList.size(); i++){
            if(studentArrayList.get(i).getName().equals(name)){
                student = studentArrayList.get(i);
            }
        }
        return student;
    }

    public Student findStudentByCode(String code){                 //tìm thông tin Sinh viên theo code
        Student student = null;
        for (int i=0; i<studentArrayList.size(); i++){
            if(studentArrayList.get(i).getCode().equals(code)){
                student = studentArrayList.get(i);
            }
        }
        return student;
    }

    public void removeStudent(String code){                        //xóa Sinh viên
        for (int i=0; i<studentArrayList.size(); i++){
            if(studentArrayList.get(i).getCode().equals(code)){
                studentArrayList.remove(i);
            }
        }
    }

    public void addCard(Card card){                                             //thêm model.Card khi Sinh viên bắt đầu mượn, chưa có ngày trả
        if(findStudentByCode(card.getStudent().getCode()) != null){
            if(findBookByCode(card.getBook().getCode()) != null){
                cardArrayList.add(card);
                for (int i=0; i<bookArrayList.size(); i++){                                          //giảm đi số lượng Sách đã mượn
                    if(bookArrayList.get(i).getCode().equals(card.getBook().getCode())){
                        int firstNum = bookArrayList.get(i).getNumber();
                        int borrowedNum = card.getBook().getNumber();
                        if(borrowedNum <= firstNum){
                            bookArrayList.get(i).setNumber(firstNum - borrowedNum);
                        }
                        else {
                            cardArrayList.remove(card);
                            System.out.println("Số lượng sách mượn vượt quá trong kho");
                        }
                    }
                }
            }
            else System.out.println("Sách này ko có trong kho");
        }
        else System.out.println("Sinh viên này ko có trong trường");
    }

    public void editCardBehindPay(String codeCard, LocalDate payDate){                 //chỉnh lại thông tin Card sau khi trả Sách
        for (int i=0; i<cardArrayList.size(); i++){                                            //thêm ngày trả Sách
            if(cardArrayList.get(i).getCode().equals(codeCard)){
                cardArrayList.get(i).setPayDate(payDate);
                for (int j=0; j<bookArrayList.size(); j++){                                           //tăng thêm số lượng Sách đã trả
                    if(bookArrayList.get(j).getCode().equals(cardArrayList.get(i).getBook().getCode())){
                        int firstNum = bookArrayList.get(j).getNumber();
                        int payNum = cardArrayList.get(i).getBook().getNumber();
                        bookArrayList.get(j).setNumber(firstNum + payNum);
                    }
                }
            }
            else System.out.println("Ko có code card này trong danh sách");
        }
    }

    public void showCard(ArrayList<Card> cardArrayList){                            //hiển thị danh sách Card
        for (Card c: cardArrayList) {
            System.out.println(c);
        }
    }

    public void editCard(String code, Card card){                    //sửa Card
        for(int i=0; i<cardArrayList.size(); i++){
            if(cardArrayList.get(i).getCode().equals(code))
                cardArrayList.set(i, card);
        }
    }

    public void removeCard(String code){                      //xóa Card
        for(int i=0; i<cardArrayList.size(); i++){
            if(cardArrayList.get(i).getCode().equals(code))
                cardArrayList.remove(i);
        }
    }

    public Card findCardByCode(String code){           //tìm thông tin Card theo code
        Card card = null;
        for (int i=0; i<cardArrayList.size(); i++){
            if(cardArrayList.get(i).getCode().equals(code)){
                card = cardArrayList.get(i);
            }
        }
        return card;
    }

    public Manager() {
    }

    public Manager(ArrayList<Book> bookArrayList, ArrayList<Student> studentArrayList, ArrayList<Card> cardArrayList) {
        this.bookArrayList = bookArrayList;
        this.studentArrayList = studentArrayList;
        this.cardArrayList = cardArrayList;
    }
}
