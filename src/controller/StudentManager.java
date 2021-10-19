package controller;

import model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentManager implements GeneralManager<Student> {
    private  ArrayList<Student> studentArrayList = new ArrayList<>();

    private StudentManager() {
    }

    private static class ManagerStudentHelper{
        private static final StudentManager INSTANCE = new StudentManager();
    }

    public static StudentManager getInstance() {
        return ManagerStudentHelper.INSTANCE;
    }

    public ArrayList<Student> getStudentArrayList() {
        return studentArrayList;
    }

    public void setStudentArrayList(ArrayList<Student> studentArrayList) {
        this.studentArrayList = studentArrayList;
    }

    @Override
    public List<Student> findAll() {
        return studentArrayList;
    }

    @Override
    public void add(Student student) {
        studentArrayList.add(student);
    }

    @Override
    public void editByCode(String code, Student student) {
        for (int i=0; i<studentArrayList.size(); i++){
            if(studentArrayList.get(i).getCode().equals(code)){
                studentArrayList.set(i, student);
            }
            else System.out.println("Mã sinh viên cần sửa ko đúng");
        }
    }

    @Override
    public void removeByCode(String code) {
        for (int i=0; i<studentArrayList.size(); i++){
            if(studentArrayList.get(i).getCode().equals(code)){
                studentArrayList.remove(i);
            }
            else System.out.println("Mã sinh viên cần xóa ko đúng");
        }
    }

    @Override
    public void showAll() {
        for (Student t:studentArrayList) {
            System.out.println(t);
        }
    }

    @Override
    public Student searchByCode(String code) {
        Student student = null;
        for (int i=0; i<studentArrayList.size(); i++){
            if(studentArrayList.get(i).getCode().equals(code)){
                student = studentArrayList.get(i);
            }
        }
        return student;
    }
}
