package storage;

import model.Book;

import java.io.*;
import java.util.ArrayList;

public class BookFile {
    //Singleton, chỉ cho phép class tạo đc 1 instance
    private static BookFile bookFile;

    private BookFile(){}

    public static BookFile getInstance(){
        if(bookFile == null){
            bookFile = new BookFile();
        }
        return bookFile;
    }

    //đọc file
    public ArrayList<Book> readFile() throws IOException, ClassNotFoundException {
        File file = new File("book.txt");
        if(!file.exists()){
            file.createNewFile();
        }
        if(file.length() >0){
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Book> bookArrayList = (ArrayList<Book>) ois.readObject();
            return bookArrayList;
        }
        else return new ArrayList<>();
    }

    //ghi file
    public void writeFile(ArrayList<Book> studentArrayList) throws IOException {
        FileOutputStream fos = new FileOutputStream("book.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(oos);
    }
}
