package controller;

import model.Book;
import model.Card;
import model.Student;
import storage.CardFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CardManager implements GeneralManager<Card> {
    ArrayList<Card> cardArrayList = new ArrayList<>();
    BookManager bookManager = BookManager.getInstance();
    StudentManager studentManager = StudentManager.getInstance();
    CardFile cardFile = CardFile.getInstance();

    public ArrayList<Card> getCardArrayList() {
        return cardArrayList;
    }

    public void setCardArrayList(ArrayList<Card> cardArrayList) {
        this.cardArrayList = cardArrayList;
    }

    public BookManager getBookManager() {
        return bookManager;
    }

    public void setBookManager(BookManager bookManager) {
        this.bookManager = bookManager;
    }

    public StudentManager getStudentManager() {
        return studentManager;
    }

    public void setStudentManager(StudentManager studentManager) {
        this.studentManager = studentManager;
    }

    public CardFile getCardFile() {
        return cardFile;
    }

    public void setCardFile(CardFile cardFile) {
        this.cardFile = cardFile;
    }

    @Override
    public List<Card> findAll() {
        return cardArrayList;
    }

    @Override
    public void add(Card card) {                                                 //thêm Card khi Sinh viên bắt đầu mượn, chưa có ngày trả
        if(studentManager.searchByCode(card.getStudent().getCode()) != null){
            if(bookManager.searchByCode(card.getBook().getCode()) != null){
                cardArrayList.add(card);
                for (int i=0; i<bookManager.bookArrayList.size(); i++){                                //giảm đi số lượng Sách đã mượn
                    if(bookManager.bookArrayList.get(i).getCode().equals(card.getBook().getCode())){
                        int firstNum = bookManager.bookArrayList.get(i).getNumber();
                        int borrowedNum = card.getBook().getNumber();
                        if(borrowedNum <= firstNum){
                            bookManager.bookArrayList.get(i).setNumber(firstNum - borrowedNum);
                        }
                        else {
                            cardArrayList.remove(card);
                            System.out.println("Số lượng sách mượn vượt quá trong kho");
                        }
                    }
                }
                try {
                    cardFile.writeFile(cardArrayList);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else System.out.println("Sách này ko có trong kho");
        }
        else System.out.println("Sinh viên này ko có trong trường");
    }

    public void editCardBehindPay(String codeCard, LocalDate payDate){                 //chỉnh lại thông tin Card sau khi trả Sách
        for (int i=0; i<cardArrayList.size(); i++){
            if(cardArrayList.get(i).getCode().equals(codeCard)){
                cardArrayList.get(i).setPayDate(payDate);                          //thêm ngày trả Sách
                for (int j=0; j<bookManager.bookArrayList.size(); j++){                                                  //tăng thêm số lượng Sách đã trả
                    if(bookManager.bookArrayList.get(j).getCode().equals(cardArrayList.get(i).getBook().getCode())){
                        int firstNum = bookManager.bookArrayList.get(j).getNumber();
                        int payNum = cardArrayList.get(i).getBook().getNumber();
                        bookManager.bookArrayList.get(j).setNumber(firstNum + payNum);
                    }
                }
                try {
                    cardFile.writeFile(cardArrayList);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else System.out.println("Ko có code card này trong danh sách");
        }
    }

    @Override
    public void editByCode(String code, Card card) {
        for(int i=0; i<cardArrayList.size(); i++){
            if(cardArrayList.get(i).getCode().equals(code)){
                cardArrayList.set(i, card);
                try {
                    cardFile.writeFile(cardArrayList);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    @Override
    public void removeByCode(String code) {
        for(int i=0; i<cardArrayList.size(); i++){
            if(cardArrayList.get(i).getCode().equals(code)){
                cardArrayList.remove(i);
                try {
                    cardFile.writeFile(cardArrayList);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void showAll() {
        for (Card c: cardArrayList) {
            System.out.println(c);
        }
    }

    @Override
    public Card searchByCode(String code) {
        Card card = null;
        for (int i=0; i<cardArrayList.size(); i++){
            if(cardArrayList.get(i).getCode().equals(code)){
                card = cardArrayList.get(i);
            }
        }
        return card;
    }

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

    public ArrayList<Card> findCardNeedPayForMonth(){               //tìm danh sách Card có sách "Quá hạn trả" vào "Cuối mỗi tháng"
        ArrayList<Card> cardArrayList1 = new ArrayList<>();
        for (int i=0; i<cardArrayList.size(); i++){
            LocalDate borrowedDate = cardArrayList.get(i).getBorrowedDate();
            LocalDate dateOfLastMonth = borrowedDate.withDayOfMonth(borrowedDate.lengthOfMonth());
            if((cardArrayList.get(i).getPayDate() == null && cardArrayList.get(i).getBorrowedDate().plusDays(15).isBefore(dateOfLastMonth))
                    || cardArrayList.get(i).getPayDate().isAfter(cardArrayList.get(i).getBorrowedDate().plusDays(15))){
                cardArrayList1.add(cardArrayList.get(i));
            }
        }
        return cardArrayList1;
    }

}
