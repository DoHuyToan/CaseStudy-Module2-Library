package storage;

import model.User;

import java.io.*;
import java.util.ArrayList;

public class UserFile {
    //Singleton, chỉ cho phép class tạo đc 1 instance
    private static UserFile userFile;

    private UserFile(){}

    public static UserFile getInstance(){
        if(userFile == null){
            userFile = new UserFile();
        }
        return userFile;
    }

    //đọc file
    public ArrayList<User> readFile() throws IOException, ClassNotFoundException {
        File file = new File("user.txt");
        if(!file.exists()){
            file.createNewFile();
        }
        if(file.length() >0){
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<User> userArrayList = (ArrayList<User>) ois.readObject();
            return userArrayList;
        }
        else return new ArrayList<>();
    }

    //ghi file
    public void writeFile(ArrayList<User> userArrayList) throws IOException {

        FileOutputStream fos = new FileOutputStream("user.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(userArrayList);
    }

//    public static void main(String[] args) {       cách kiểm tra ngay tại chỗ
//        UserFile userFile = new UserFile();
//        ArrayList<User> userArrayList = new ArrayList<>();
//        userArrayList.add(new User("C08", "KA", "123"));
//        try {
//            userFile.writeFile(userArrayList);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
