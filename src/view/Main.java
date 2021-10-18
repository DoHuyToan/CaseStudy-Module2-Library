package view;

import controller.Manager;
import model.Book;
import model.Card;
import model.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Book> bookArrayList = new ArrayList<>();
        ArrayList<Student> studentArrayList = new ArrayList<>();
        ArrayList<Card> cardArrayList = new ArrayList<>();


        Book book1 = new Book("Đắc nhân tâm", "s1", 5);
        Book book2 = new Book("Nhà giả kim", "s2", 6);
        Book book3 = new Book("Dạy làm giàu", "s3", 8);
        bookArrayList.add(book1);
        bookArrayList.add(book2);
        bookArrayList.add(book3);
        Student toan = new Student("Toàn", "sv1", "c08", "1989");
        Student cuong = new Student("Cuong", "sv2", "c08", "2000");
        studentArrayList.add(toan);
        studentArrayList.add(cuong);

        Manager thuVienBK = new Manager(bookArrayList, studentArrayList, cardArrayList);

        int choice;
        do {
            System.out.println("Menu");
            System.out.println("1. Thêm Sách");
            System.out.println("2. Hiển thị danh sách Sách");
            System.out.println("3. Sửa Sách");
            System.out.println("4. Xóa Sách");
            System.out.println("5. Thêm Sinh viên");
            System.out.println("6. Hiển thị danh sách Sinh viên");
            System.out.println("7. Sửa Sinh viên");
            System.out.println("8. Xóa Sinh viên");
            System.out.println("9. Thêm Card khi mượn Sách");
            System.out.println("10. Chỉnh Card khi trả Sách");
            System.out.println("11. Hiển thị danh sách Card");
            System.out.println("12. Sửa Card");
            System.out.println("13. Xóa Card");
            System.out.println("14. Danh sách Sách quá hạn cần phải trả Hiện tại");
            System.out.println("15. Danh sách Sách quá hạn cần trả Cuối mỗi tháng");
            System.out.println("0. Exit");
            System.out.println("Chọn Menu");
            Scanner inputChoice = new Scanner(System.in);
            choice = inputChoice.nextInt();
            switch (choice){
                case 1:
                    thuVienBK.addBook(creatBook());
                    break;
                case 2:
                    thuVienBK.showBook();
                    break;
                case 3:
                    thuVienBK.editBook(inputCode(), creatBook());
                    break;
                case 4:
                    thuVienBK.removeBook(inputCode());
                    break;
                case 5:
                    thuVienBK.addStudent(creatStudent());
                    break;
                case 6:
                    thuVienBK.showStudent();
                    break;
                case 7:
                    thuVienBK.editStudent(inputCode(), creatStudent());
                    break;
                case 8:
                    thuVienBK.removeStudent(inputCode());
                    break;
                case 9:
                    thuVienBK.addCard(creatCard());
                    break;
                case 10:
                    thuVienBK.editCardBehindPay(inputCode(), inputPayDate());
                    break;
                case 11:
                    thuVienBK.showCard(cardArrayList);
                    break;
                case 12:
                    thuVienBK.editCard(inputCode(), creatCard());
                    break;
                case 13:
                    thuVienBK.removeCard(inputCode());
                    break;
                case 14:
                    thuVienBK.showCard(thuVienBK.findCardNeedPay());
                    break;
                case 15:
                    thuVienBK.showCard(thuVienBK.findCardNeedPayForMonth());
                    break;
            }
        } while (choice!=0);
    }

    private static Card creatCard(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập mã Card");
        String code = scanner.nextLine();
        System.out.println("Nhập Sinh viên");
        Student student = creatStudent();
        System.out.println("Nhập Sách");
        Book book = creatBook();
        System.out.println("Nhập ngày tháng thuê Sách");
        LocalDate borrowedDate = inputBorrowedDate();
        Card card = new Card(code, book, student, borrowedDate);
        return card;
    }

    private static LocalDate inputBorrowedDate(){
        System.out.println("Nhập Năm thuê");
        Scanner inputYear = new Scanner(System.in);
        int year = inputYear.nextInt();
        System.out.println("Nhập Tháng thuê");
        Scanner inputMonth = new Scanner(System.in);
        int month = inputMonth.nextInt();
        System.out.println("Nhập Ngày thuê");
        Scanner inputDate = new Scanner(System.in);
        int date = inputDate.nextInt();
        LocalDate borrowedDate = LocalDate.of(year,month, date);
        return borrowedDate;
    }

    private static LocalDate inputPayDate(){
        System.out.println("Nhập Năm trả");
        Scanner inputYear = new Scanner(System.in);
        int year = inputYear.nextInt();
        System.out.println("Nhập Tháng trả");
        Scanner inputMonth = new Scanner(System.in);
        int month = inputMonth.nextInt();
        System.out.println("Nhập Ngày trả");
        Scanner inputDate = new Scanner(System.in);
        int date = inputDate.nextInt();
        LocalDate payDate = LocalDate.of(year,month, date);
        return payDate;
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

    private static String inputCodeStudent(){
        System.out.println("Nhập code sinh viên");
        Scanner inputCodeStudent = new Scanner(System.in);
        String codeStudent = inputCodeStudent.nextLine();
        return codeStudent;
    }
}
