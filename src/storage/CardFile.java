package storage;

import model.Card;

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
    public ArrayList<Card> readFile() throws IOException, ClassNotFoundException {
        File file = new File("card.txt");
        if(!file.exists()){
            file.createNewFile();
        }
        if(file.length() >0){
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Card> cardArrayList = (ArrayList<Card>) ois.readObject();
            return cardArrayList;
        }
        else return new ArrayList<>();
    }

    //ghi file
    public void writeFile(ArrayList<Card> cardArrayList) throws IOException {
        FileOutputStream fos = new FileOutputStream("card.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(oos);
    }
}
