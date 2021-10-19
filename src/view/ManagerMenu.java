package view;

import controller.CardManager;

import java.util.Scanner;

public class ManagerMenu {

    private ManagerMenu(){}

    private static class ManagerMenuHelper{
        private static final ManagerMenu INSTANCE = new ManagerMenu();
    }

    public static ManagerMenu getInstance(){
        return ManagerMenuHelper.INSTANCE;
    }

    public void runManager(){
        StudentMenu studentMenu = new StudentMenu();
        BookMenu bookMenu = new BookMenu();
        CardMenu cardMenu = new CardMenu();

        Scanner inputChoice = new Scanner(System.in);
        int choice;
        do {
            System.out.println("Menu");
            System.out.println("1. Quản lý Sách");
            System.out.println("2. Quản lý Sinh viên");
            System.out.println("3. Quản lý Card");
            System.out.println("0. Exit");
            System.out.println("Chọn Menu");
            choice = inputChoice.nextInt();
            switch (choice){
                case 1:
                    bookMenu.runBook();
                    break;
                case 2:
                    studentMenu.runStudent();
                    break;
                case 3:
                    cardMenu.runCard();
                    break;
            }
        } while (choice!=0);
    }
}
