package com.login;

import com.dao.DatabaseService;
import com.dao.LoginDao;
import com.service.BookService;
import com.service.StudentService;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class LoginService {
    Scanner sc = new Scanner(System.in);

    public void doLogin() throws  ClassNotFoundException, SQLException {
        System.out.println("Enter Username: ");
        String userName = sc.nextLine();
        System.out.println("Enter Password: ");
        String password = sc.nextLine();

        try (Connection conn = DatabaseService.getConnection()) {
            LoginDao loginDao = new LoginDao();
            String userType = loginDao.doLogin(conn, userName, password);
            if (userType == null) {
                System.out.println("Invalid user!");
                return;
            }
            System.out.println("Successful login as a " + userType + "! Please select from options below");
            if (userType.equals("admin")){
                admin_functionality();
            }else {
                student_functionality();
            }
        }
    }

    public void admin_functionality() throws SQLException, ClassNotFoundException {
        int choice = 0;
        while (choice != 7) {
            System.out.println("------------Admin Functionality-----------");
            System.out.println("1. Search a book.");
            System.out.println("2. Add new book");
            System.out.println("3. Update book quantity.");
            System.out.println("4. Display all books.");
            System.out.println("5. Register new student.");
            System.out.println("6. Display all students");
            System.out.println("7. Exit application.");
            System.out.println("------------------------------------------");
            System.out.println("Enter your choice:");
            choice = sc.nextInt();

            Connection conn = DatabaseService.getConnection();
            BookService bookService = new BookService();
            StudentService studentService = new StudentService();
            switch (choice){
                case 1:
                    searchBook();
                    break;
                case 2:
                    bookService.addBook(conn);
                    break;
                case 3:
                    bookService.updateQuantity(conn);
                    break;
                case 4:
                    bookService.displayAllBooks(conn);
                    break;
                case 5:
                    studentService.addStudent(conn);
                    break;
                case 6:
                    studentService.displayAllStudents(conn);
                    break;
                case 7:
                    System.out.println("You chose to exit the program.");
                    break;
                default:
                    System.out.println("Incorrect choice! Try again.");
            }
        }
    }

    public void student_functionality() throws SQLException, ClassNotFoundException {
        int choice = 0;
        while (choice != 7) {
            System.out.println("------------Student Functionality-----------");
            System.out.println("1. Search a book.");
            System.out.println("2. Display all books.");
            System.out.println("3. Borrow a book");
            System.out.println("4. Return a book");
            System.out.println("5. Exit application.");
            System.out.println("------------------------------------------");
            System.out.println("Enter your choice:");
            choice = sc.nextInt();

            Connection conn = DatabaseService.getConnection();
            BookService bookService = new BookService();
            switch (choice){
                case 1:
                    searchBook();
                    break;
                case 2:
                    bookService.displayAllBooks(conn);
                    break;
                case 3:
                    bookService.borrowBook(conn);
                    break;
                case 4:
                    bookService.returnBook(conn);
                    break;
                case 5:
                    System.out.println("You chose to exit the program.");
                    break;
                default:
                    System.out.println("Incorrect choice! Try again.");
            }
        }
    }

    private void searchBook() throws ClassNotFoundException, SQLException{
        Connection conn = DatabaseService.getConnection();
        System.out.println("1. Search with Serial No.");
        System.out.println("2. Search with Author's Name.");
        System.out.println("3. Search with Book Name.");
        int choice = sc.nextInt();
        BookService bookService = new BookService();
        switch(choice){
            case 1:
                bookService.search_SerialNo(conn);
                break;
            case 2:
                bookService.search_AuthorName(conn);
                break;
            case 3:
                bookService.search_Name(conn);
                break;
            default:
                System.out.println("Incorrect choice! Try again.");
        }
    }
}