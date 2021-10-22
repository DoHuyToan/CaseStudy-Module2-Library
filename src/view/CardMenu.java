package view;

import controller.BookManager;
import controller.CardManager;
import controller.StudentManager;
import model.Book;
import model.Card;
import model.Student;
import storage.CardFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class CardMenu {
    CardManager cardManager = CardManager.getInstance();
    BookManager bookManager = BookManager.getInstance();
    StudentManager studentManager = StudentManager.getInstance();

    Scanner inputChoice = new Scanner(System.in);
    int choice;

    public void runCard() {

        try {
            cardManager.setCardArrayList(CardFile.getInstance().readFile());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        do {
            System.out.println("Menu");
            System.out.println("1. Thêm Card khi mượn Sách");
            System.out.println("2. Thêm ngày sau khi trả Sách");
            System.out.println("3. Sửa Card");
            System.out.println("4. Xóa Card");
            System.out.println("5. Hiển thị danh sách Card");
            System.out.println("6. Hiển thị danh sách Sách quá hạn chưa trả");
            System.out.println("7. Liệt kê danh sách Sách quá hạn cuối mỗi tháng");
            System.out.println("8. Tìm card theo code");
            System.out.println("0. Exit");
            System.out.println("Chọn Menu");
            choice = inputChoice.nextInt();
            switch (choice) {
                case 1:
                    cardManager.addCard(inputCodeCard(), inputCodeStudent(), creatBook(), inputBorrowedDate());
                    break;
                case 2:
                    cardManager.addPayDate(inputCodeCard(), inputPayDate());
                    break;
                case 3:
                    cardManager.editByCode(inputCodeCard(), creatCard());
                    break;
                case 4:
                    cardManager.removeByCode(inputCodeCard());
                    break;
                case 5:
                    cardManager.showAll(cardManager.getCardArrayList());
                    break;
                case 6:
                    cardManager.showAll(cardManager.findCardNeedPay());
                    break;
                case 7:
                    cardManager.showAll(cardManager.findCardNeedPayForMonth());
                    break;
                case 8:
                    System.out.println(cardManager.searchByCode(inputCodeCard()));
                    break;
            }
        } while (choice != 0);
    }

    private static Card creatCard() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập mã Card");
        String code = scanner.nextLine();
        System.out.println("Nhập code Sinh viên");
        Student student = addStudentIntoCard();
        System.out.println("Nhập Sách");
        Book book = addBookIntoCard();
        System.out.println("Nhập ngày tháng thuê Sách");
        LocalDate borrowedDate = inputBorrowedDate();
        Card card = new Card(code, student, borrowedDate);
        return card;
    }

    private static Student addStudentIntoCard() {
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

    private static Book addBookIntoCard() {
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

    private static LocalDate inputBorrowedDate() {
        System.out.println("Nhập Năm thuê");
        Scanner inputYear = new Scanner(System.in);
        int year = inputYear.nextInt();
        System.out.println("Nhập Tháng thuê");
        Scanner inputMonth = new Scanner(System.in);
        int month = inputMonth.nextInt();
        System.out.println("Nhập Ngày thuê");
        Scanner inputDate = new Scanner(System.in);
        int date = inputDate.nextInt();
        LocalDate borrowedDate = LocalDate.of(year, month, date);
        return borrowedDate;
    }

    private static LocalDate inputPayDate() {
        System.out.println("Nhập Năm trả");
        Scanner inputYear = new Scanner(System.in);
        int year = inputYear.nextInt();
        System.out.println("Nhập Tháng trả");
        Scanner inputMonth = new Scanner(System.in);
        int month = inputMonth.nextInt();
        System.out.println("Nhập Ngày trả");
        Scanner inputDate = new Scanner(System.in);
        int date = inputDate.nextInt();
        LocalDate payDate = LocalDate.of(year, month, date);
        if (payDate.isBefore(LocalDate.now()) || payDate.equals(LocalDate.now())) {
            return payDate;
        } else {
            System.out.println("Ngày trả ko được lớn hơn ngày hiện tại");
            return null;
        }
    }

    private static String inputCodeBook() {
        System.out.println("Nhập code Book");
        Scanner inputCode = new Scanner(System.in);
        String code = inputCode.nextLine();
        return code;
    }

    private static String inputCodeStudent() {
        System.out.println("Nhập code Sinh viên");
        Scanner inputCode = new Scanner(System.in);
        String code = inputCode.nextLine();
        return code;
    }

    private static String inputCodeCard() {
        System.out.println("Nhập code Card");
        Scanner inputCode = new Scanner(System.in);
        String code = inputCode.nextLine();
        return code;
    }

    private static int inputNumBook() {
        System.out.println("Nhập số sách");
        Scanner inputCode = new Scanner(System.in);
        int numBook = inputCode.nextInt();
        return numBook;
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

}
