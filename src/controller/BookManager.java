package controller;

import model.Book;
import storage.BookFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookManager implements GeneralManager<Book> {
    private static BookManager bookManager;
    ArrayList<Book> bookArrayList = new ArrayList<>();
    BookFile bookFile =BookFile.getInstance();

    private BookManager() {
    }

    private static class BookManagerHelper{
        private static final BookManager INSTANCE = new BookManager();
    }

    public static BookManager getInstance() {
        return BookManager.BookManagerHelper.INSTANCE;
    }

    public ArrayList<Book> getBookArrayList() {
        return bookArrayList;
    }

    public void setBookArrayList(ArrayList<Book> bookArrayList) {
        this.bookArrayList = bookArrayList;
    }

    public void decreaseNum(String code, int decreaseNum){
        for (int i=0; i<bookArrayList.size(); i++){
            if(bookArrayList.get(i).getCode().equals(code)){
                int firstNum = bookArrayList.get(i).getNumber();
                if(decreaseNum <= firstNum){
                    bookArrayList.get(i).setNumber(firstNum - decreaseNum);
                    try {
                        bookFile.writeFile(bookArrayList);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else System.out.println("Số sách mượn vượt quá trong kho");
            }
            else System.out.println("Mã sách cần sửa ko đúng");
        }

    }

    public void plusNum(String code, int payNum){
        for (int i=0; i<bookArrayList.size(); i++){
            if(bookArrayList.get(i).getCode().equals(code)){
                int firstNum = bookArrayList.get(i).getNumber();
                bookArrayList.get(i).setNumber(firstNum + payNum);
                try {
                    bookFile.writeFile(bookArrayList);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else System.out.println("Mã sách cần sửa ko đúng");
        }

    }

    @Override
    public List<Book> findAll() {
        return bookArrayList;
    }

    @Override
    public void add(Book book) {
        bookArrayList.add(book);
        try {
            bookFile.writeFile(bookArrayList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editByCode(String code, Book book) {
        for (int i=0; i<bookArrayList.size(); i++){
            if(bookArrayList.get(i).getCode().equals(code)){
                bookArrayList.set(i, book);
            }
            else System.out.println("Mã sách cần sửa ko đúng");
        }
        try {
            bookFile.writeFile(bookArrayList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeByCode(String code) {
        for (int i=0; i<bookArrayList.size(); i++){
            if(bookArrayList.get(i).getCode().equals(code)){
                bookArrayList.remove(i);
            }
            else System.out.println("Mã sách cần xóa ko đúng");
        }
        try {
            bookFile.writeFile(bookArrayList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showAll() {
        for (Book b:bookArrayList) {
            System.out.println(b);
        }
    }

    @Override
    public Book searchByCode(String code) {
        Book book = null;
        for (int i=0; i<bookArrayList.size(); i++){
            if(bookArrayList.get(i).getCode().equals(code)){
                book = bookArrayList.get(i);
            }
        }
        return book;
    }
}
