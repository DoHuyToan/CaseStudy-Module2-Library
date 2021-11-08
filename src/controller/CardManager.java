package controller;

import model.Book;
import model.Card;
import model.Student;
import storage.CardFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class CardManager {
    CardFile cardFile = CardFile.getInstance();
    ArrayList<Card> cardArrayList = new ArrayList<>();
    private static final BookManager BOOK_MANAGER = BookManager.getInstance();
    private static final StudentManager STUDENT_MANAGER = StudentManager.getInstance();


    private CardManager() {
    }

    public static CardManager getInstance() {
        return CardManager.CardManagerHelper.INSTANCE;
    }

    private static class CardManagerHelper{
        private static final CardManager INSTANCE = new CardManager();
    }

    public Card searchCardByCode(String codeCard){
        Card card = null;
        for (int i=0; i<cardArrayList.size(); i++){
            if(cardArrayList.get(i).getCode().equals(codeCard)){
                card = cardArrayList.get(i);
                break;
            }
        }
        return card;
    }

    //tạo Card khi Sinh viên bắt đầu mượn, chưa có ngày trả
    public void addCard(String codeCard, String codeStudent, Book book, LocalDate borrowedDate) {
        if(searchCardByCode(codeCard) == null){
            Student student = STUDENT_MANAGER.searchByCode(codeStudent);
            if (student != null){
                if(! findCodeStudentNeedPay(codeStudent)){
                    Card card = new Card(codeCard, student, book, borrowedDate);
                    cardArrayList.add(card);
                    BOOK_MANAGER.decreaseNum(book.getCode(), book.getNumber());
                }
                else System.out.println("Sinh viên thuê quá hạn chưa trả ko đc mượn tiếp");
            }
        }
        else System.out.println("Mã thẻ đã tồn tại");
        try {
            cardFile.writeFile(cardArrayList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addPayDate(String codeCard, LocalDate payDate){                 ////thêm ngày trả sau khi trả Sách
        for (int i=0; i<cardArrayList.size(); i++){
            if(cardArrayList.get(i).getCode().equals(codeCard)){
                cardArrayList.get(i).setPayDate(payDate);
                Book book = cardArrayList.get(i).getBook();
                BOOK_MANAGER.plusNum(book.getCode(), book.getNumber());
                break;
            }
        }
        try {
            cardFile.writeFile(cardArrayList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean findCodeStudentNeedPay(String code){                   //tìm Sinh viên có Card "Quá hạn trả" mà "Hiện tại Chưa trả"
        boolean isPass = false;
        for (int i=0; i<cardArrayList.size(); i++){
            if(cardArrayList.get(i).getPayDate() == null
                    && cardArrayList.get(i).getBorrowedDate().plusDays(15).isBefore(LocalDate.now())
                    && cardArrayList.get(i).getStudent().getCode().equals(code)) {
                isPass = true;
                break;
            }
        }
        return isPass;
    }



    public void editByCode(String code, Card card) {
        for(int i=0; i<cardArrayList.size(); i++){
            if(cardArrayList.get(i).getCode().equals(code)){
                cardArrayList.set(i, card);
                break;
            }
            else System.out.println("Code này ko có trong Card");
        }
        try {
            cardFile.writeFile(cardArrayList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeByCode(String code) {
        for(int i=0; i<cardArrayList.size(); i++){
            if(cardArrayList.get(i).getCode().equals(code)){
                cardArrayList.remove(i);
            }
            else System.out.println("Code này ko có trong Card");
        }
        try {
            cardFile.writeFile(cardArrayList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showAll(ArrayList<Card> cardArrayList) {
        for (Card c: cardArrayList) {
            System.out.println(c);
        }
    }

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
        ArrayList<Card> cardArrayList1 = new ArrayList<>();
        Card card = null;
        for (int i=0; i<cardArrayList.size(); i++){
            if(cardArrayList.get(i).getPayDate() == null
                    && cardArrayList.get(i).getBorrowedDate().plusDays(15).isBefore(LocalDate.now())){
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

    public ArrayList<Card> getCardArrayList() {
        return cardArrayList;
    }

    public void setCardArrayList(ArrayList<Card> cardArrayList) {
        this.cardArrayList = cardArrayList;
    }

    public CardFile getCardFile() {
        return cardFile;
    }

    public void setCardFile(CardFile cardFile) {
        this.cardFile = cardFile;
    }


}
