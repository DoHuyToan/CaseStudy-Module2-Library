package storage;

import model.Student;

import java.io.*;
import java.util.ArrayList;

public class StudentFile {
    //Singleton, chỉ cho phép class tạo đc 1 instance
    private static StudentFile studentFile;

    private StudentFile(){}

    public static StudentFile getInstance(){
        if(studentFile == null){
            studentFile = new StudentFile();
        }
        return studentFile;
    }

    //đọc file
    public ArrayList<Student> readFile() throws IOException, ClassNotFoundException {
        File file = new File("student.txt");
        if(!file.exists()){
            file.createNewFile();
        }
        if(file.length() >0){
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Student> studentArrayList = (ArrayList<Student>) ois.readObject();
            return studentArrayList;
        }
        else return new ArrayList<>();
    }

    //ghi file
    public void writeFile(ArrayList<Student> studentArrayList) throws IOException {
        FileOutputStream fos = new FileOutputStream("student.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(oos);
    }
}
