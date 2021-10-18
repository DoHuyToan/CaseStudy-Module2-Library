package storage;

import model.Student;

import java.io.*;
import java.util.ArrayList;

public class CardFile {
    //Singleton, chỉ cho phép class tạo đc 1 instance
    private static CardFile cardFile;

    private CardFile(){}

    public static CardFile getInstance(){
        if(cardFile == null){
            cardFile = new CardFile();
        }
        return cardFile;
    }

    //đọc file
    public ArrayList<Student> readFile() throws IOException, ClassNotFoundException {
        File file = new File("card.txt");
        if(!file.exists()){
            file.createNewFile();
        }
        if(file.length() >0){
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Student> cardArrayList = (ArrayList<Student>) ois.readObject();
            return cardArrayList;
        }
        else return new ArrayList<>();
    }

    //ghi file
    public void writeFile(ArrayList<Student> cardArrayList) throws IOException {
        FileOutputStream fos = new FileOutputStream("card.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(oos);
    }
}
