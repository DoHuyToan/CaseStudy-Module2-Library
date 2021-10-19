package view;

import controller.StudentManager;
import model.Student;

import java.util.Scanner;

public class StudentMenu {
    public void runStudent(){
        StudentManager studentManager = StudentManager.getInstance();
        Scanner inputChoice = new Scanner(System.in);
        int choice;
        do {
            System.out.println("Menu");
            System.out.println("1. Thêm Sinh viên");
            System.out.println("2. Sửa Sinh viên");
            System.out.println("3. Xóa Sinh viên");
            System.out.println("4. Hiển thị danh sách Sinh viên");
            System.out.println("0. Exit");
            System.out.println("Chọn Menu");
            choice = inputChoice.nextInt();
            switch (choice){
                case 1:
                    studentManager.add(creatStudent());
                    break;
                case 2:
                    studentManager.editByCode(inputCode(), creatStudent());
                    break;
                case 3:
                    studentManager.removeByCode(inputCode());
                    break;
                case 4:
                    studentManager.showAll();
                    break;
            }
        } while (choice!=0);
    }

    private static Student creatStudent(){
        System.out.println("Nhập tên Sinh viên");
        Scanner inputName = new Scanner(System.in);
        String name = inputName.nextLine();
        System.out.println("Nhập mã Sinh viên");
        Scanner inputCode = new Scanner(System.in);
        String code = inputCode.nextLine();
        System.out.println("Nhập tên lớp");
        Scanner inputClassRoom = new Scanner(System.in);
        String classRoom = inputClassRoom.nextLine();
        System.out.println("Nhập ngày sinh");
        Scanner inputBirthDay = new Scanner(System.in);
        String birthDay = inputBirthDay.nextLine();
        Student student = new Student(name, code, classRoom, birthDay);
        return student;
    }

    private static String inputCode(){
        System.out.println("Nhập code");
        Scanner inputCode = new Scanner(System.in);
        String code = inputCode.nextLine();
        return code;
    }
}
