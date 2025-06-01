package com.service;

import com.dao.StudentDao;
import com.dto.Book;
import com.dao.BookDao;
import com.dto.BookingDetails;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class BookService {
    Scanner sc = new Scanner(System.in);

    public void search_SerialNo(Connection conn) throws SQLException {
        System.out.println("Enter Serial No:");
        int sr_no = sc.nextInt();
        BookDao bookDao = new BookDao();
        Book book = bookDao.getBook_SerialNo(conn, sr_no);
        if (book != null){
            System.out.println("Book Info:");
            System.out.println("ID: " + book.getId() + ", Serial No: " + book.getSr_no() +
                    ", Book Name: " + book.getBookName() + ", Author Name: " + book.getAuthorName() +
                    ", Book Quantity: " + book.getQty());
        } else {
            System.out.println("No such book found");
        }
        System.out.println();
    }

    public void search_AuthorName(Connection conn) throws SQLException {
        System.out.println("Enter Author's name:");
        String name = sc.nextLine();
        BookDao bookDao = new BookDao();
        Book book = bookDao.getBook_AuthorName(conn, name);
        if (book != null){
            System.out.println("Book Info:");
            System.out.println("ID: " + book.getId() + ", Serial No: " + book.getSr_no() +
                    ", Book Name: " + book.getBookName() + ", Author Name: " + book.getAuthorName() +
                    ", Book Quantity: " + book.getQty());
        } else {
            System.out.println("No such book found");
        }
    }

    public void search_Name(Connection conn) throws SQLException {
        System.out.println("Enter Book name:");
        String name = sc.nextLine();
        BookDao bookDao = new BookDao();
        Book book = bookDao.getBook_BookName(conn, name);
        if (book != null){
            System.out.println("Book Info:");
            System.out.println("ID: " + book.getId() + ", Serial No: " + book.getSr_no() +
                    ", Book Name: " + book.getBookName() + ", Author Name: " + book.getAuthorName() +
                    ", Book Quantity: " + book.getQty());
        } else {
            System.out.println("No such book found");
        }
    }

    public void addBook(Connection conn) throws SQLException {
        Book book = new Book();
        System.out.println("Write book info:");
        System.out.println("Enter serial no:");
        book.setSr_no(sc.nextInt());
        System.out.println("Enter Book Name:");
        sc.nextLine();
        book.setBookName(sc.nextLine());
        System.out.println("Enter Author Name:");
        book.setAuthorName(sc.nextLine());
        System.out.println("Enter Book Quantity:");
        book.setQty(sc.nextInt());
        BookDao dao = new BookDao();
        Book book2 = dao.getBook_SerialNoOrBookName(conn, book.getSr_no(), book.getBookName());
        if (book2 != null){
            System.out.println("Such book already exists.");
            return;
        }
        dao.insertBook(conn, book);
    }

    public void displayAllBooks(Connection conn) throws SQLException {
        BookDao dao = new BookDao();
        List<Book> books = dao.getAllBooks(conn);
        for (Book book : books){
            System.out.println("ID: " + book.getId() + ", Serial No: " + book.getSr_no() +
                    ", Book Name: " + book.getBookName() + ", Author Name: " + book.getAuthorName() +
                    ", Book Quantity: " + book.getQty());
        }
    }

    public void updateQuantity(Connection conn) throws SQLException {
        System.out.println("Enter Book Serial No:");
        int sr_no = sc.nextInt();
        System.out.println("Enter new quantity:");
        int qty = sc.nextInt();
        BookDao dao = new BookDao();
        dao.updateBookQty(conn, sr_no, qty);
    }

    public void borrowBook(Connection conn) throws SQLException {
        StudentDao dao = new StudentDao();
        System.out.println("Enter your Registration Number:");
        String regNo = sc.nextLine();
        boolean isStdExist = dao.getStudent_regNo(conn, regNo);
        if (!isStdExist) {
            System.out.println("Student with such Registration Number doesn't exist.");
            return;
        }
        displayAllBooks(conn);
        System.out.println("Enter Serial Number of wanted book:");
        int sr_no = sc.nextInt();
        BookDao bookDao = new BookDao();
        Book book = bookDao.getBook_SerialNo(conn, sr_no);
        if (book == null || book.getQty() == 0){
            System.out.println("No such book in database or there is no such books left.");
        }
        int id = dao.getStudentID_regNo(conn, regNo);

        dao.insertBookingDetail(conn, id, book.getId(), 1);
        bookDao.updateBookQty(conn, sr_no, book.getQty() - 1);
    }

    public void returnBook(Connection conn) throws SQLException {
        StudentDao dao = new StudentDao();
        System.out.println("Enter your Registration Number:");
        String regNo = sc.nextLine();
        boolean isStdExist = dao.getStudent_regNo(conn, regNo);
        if (!isStdExist) {
            System.out.println("Student with such Registration Number doesn't exist.");
            return;
        }
        int id = dao.getStudentID_regNo(conn, regNo);
        List<BookingDetails> bookingDetails = dao.getBookDetailsId(conn, id);

        bookingDetails.stream().forEach(b -> System.out.println(b.getSr_no() + "\t\t\t" + b.getBookName() + "\t\t\t" + b.getAuthorName()));
        System.out.println("Enter Serial Number of book to return:");
        int sr_no = sc.nextInt();
        BookingDetails filterDetails = bookingDetails.stream().filter(b -> b.getSr_no() == sr_no).findAny().orElse(null);

        BookDao bookDao = new BookDao();
        Book book = bookDao.getBook_SerialNo(conn, sr_no);

        bookDao.updateBookQty(conn, sr_no, book.getQty() + 1);
        dao.deleteBookingDetail(conn, filterDetails.getId());
    }
}
