package view;

import controller.BookManager;
import model.Book;

import java.util.Scanner;

public class BookMenu {

    public void runBook(){
        BookManager bookManager = BookManager.getInstance();
        Scanner inputChoice = new Scanner(System.in);
        int choice;
        do {
            System.out.println("Menu");
            System.out.println("1. Thêm Sách");
            System.out.println("2. Sửa Sách");
            System.out.println("3. Xóa Sách");
            System.out.println("4. Hiển thị danh sách Sách");
            System.out.println("0. Exit");
            System.out.println("Chọn Menu");
            choice = inputChoice.nextInt();
            switch (choice){
                case 1:
                    bookManager.add(creatBook());
                    break;
                case 2:
                    bookManager.editByCode(inputCode(), creatBook());
                    break;
                case 3:
                    bookManager.removeByCode(inputCode());
                    break;
                case 4:
                    bookManager.showAll();
                    break;
            }
        } while (choice!=0);
    }

    private static Book creatBook(){
        System.out.println("Nhập tên Sách");
        Scanner inputName = new Scanner(System.in);
        String name = inputName.nextLine();
        System.out.println("Nhập mã Sách");
        Scanner inputCode = new Scanner(System.in);
        String code = inputCode.nextLine();
        System.out.println("Nhập số sách");
        Scanner inputNumber = new Scanner(System.in);
        int number = inputNumber.nextInt();
        Book book = new Book(name, code, number);
        return book;
    }

    private static String inputCode(){
        System.out.println("Nhập code");
        Scanner inputCode = new Scanner(System.in);
        String code = inputCode.nextLine();
        return code;
    }


}
