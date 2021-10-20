package view;

import controller.UserManager;
import model.User;
import storage.UserFile;

import javax.imageio.IIOException;
import java.io.IOException;
import java.util.Scanner;

public class UserMenu {
    UserManager userManager = new UserManager();
    private static final ManagerMenu MANAGER_MENU = ManagerMenu.getInstance();

    Scanner inputString = new Scanner(System.in);
    public void runUser(){
        try {
            userManager.setUserArrayList(UserFile.getInstance().readFile());
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }

        Scanner inputChoise = new Scanner(System.in);

        int choice;

        while (true){
            System.out.println("Quản lý thư viện");
            System.out.println("1. Đăng nhập");
            System.out.println("2. Đăng ký");
            System.out.println("0. Exit");

            choice = inputChoise.nextInt();
            switch (choice){
                case 1:
                    System.out.println("Nhập mã người dùng");
                    String code = inputString.nextLine();
                    System.out.println("Nhập tài khoản người dùng");
                    String account = inputString.nextLine();
                    System.out.println("Nhập mật khẩu");
                    String password = inputString.nextLine();
                    User userLogin = new User(code, account, password);

                    if(userManager.findUser(userLogin)){
                        MANAGER_MENU.runMenu();
                    }
                    else{
                        System.out.println("Tài khoản hoặc Mật khẩu ko chính xác");
                    }
                    break;
                case 2:
                    userManager.add(creatUser());
                    break;
            }
        }
    }

    private static User creatUser(){
        Scanner inputString = new Scanner(System.in);
        System.out.println("Tạo mã người dùng");
        String code = inputString.nextLine();
        System.out.println("Tạo tài khoản người dùng");
        String account = inputString.nextLine();
        System.out.println("Tạo mật khẩu");
        String password = inputString.nextLine();
        User newUser = new User(code, account, password);
        return newUser;
    }
}
