package com.dao;

import com.dto.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookDao {
    Scanner sc = new Scanner(System.in);

    public Book getBook_SerialNo(Connection conn, int sr_no) throws SQLException {
        String query = "select * from books where sr_no = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, sr_no);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Book book = new Book();
                    book.setId(rs.getInt("id"));
                    book.setSr_no(rs.getInt("sr_no"));
                    book.setBookName(rs.getString("NAME"));
                    book.setAuthorName(rs.getString("author_name"));
                    book.setQty(rs.getInt("qty"));
                    return book;
                }
            }
        }
        return null;
    }

    public Book getBook_AuthorName(Connection conn, String authorName) throws SQLException {
        String query = "select * from books where author_name = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, authorName);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Book book = new Book();
                    book.setId(rs.getInt("id"));
                    book.setSr_no(rs.getInt("sr_no"));
                    book.setBookName(rs.getString("NAME"));
                    book.setAuthorName(rs.getString("author_name"));
                    book.setQty(rs.getInt("qty"));
                    return book;
                }
            }
        }
        return null;
    }

    public Book getBook_BookName(Connection conn, String bookName) throws SQLException {
        String query = "select * from books where NAME = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, bookName);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Book book = new Book();
                    book.setId(rs.getInt("id"));
                    book.setSr_no(rs.getInt("sr_no"));
                    book.setBookName(rs.getString("NAME"));
                    book.setAuthorName(rs.getString("author_name"));
                    book.setQty(rs.getInt("qty"));
                    return book;
                }
            }
        }
        return null;
    }

    public Book getBook_SerialNoOrBookName(Connection conn, int sr_no, String bookName) throws SQLException {
        String query = "select * from books where sr_no = ? or NAME = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, sr_no);
            ps.setString(2, bookName);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Book book = new Book();
                    book.setId(rs.getInt("id"));
                    book.setSr_no(rs.getInt("sr_no"));
                    book.setBookName(rs.getString("NAME"));
                    book.setAuthorName(rs.getString("author_name"));
                    book.setQty(rs.getInt("qty"));
                    return book;
                }
            }
        }
        return null;
    }

    public List<Book> getAllBooks(Connection conn) throws SQLException {
        String query = "select * from books";
        List<Book> books = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            try (ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                    Book book = new Book();
                    book.setId(rs.getInt("id"));
                    book.setSr_no(rs.getInt("sr_no"));
                    book.setBookName(rs.getString("NAME"));
                    book.setAuthorName(rs.getString("author_name"));
                    book.setQty(rs.getInt("qty"));
                    books.add(book);
                }
            }
        }
        return books;
    }

    public void insertBook(Connection conn, Book book) throws SQLException {
        String query = "INSERT INTO books(sr_no, NAME, author_name, qty) VALUES(?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, book.getSr_no());
            ps.setString(2, book.getBookName());
            ps.setString(3, book.getAuthorName());
            ps.setInt(4, book.getQty());
            int rows_affected = ps.executeUpdate();
            if (rows_affected > 0)
                System.out.println("Inserting Book was successful.");
            else
                System.out.println("Book insertion failed");
        }
    }

    public void updateBookQty(Connection conn, int sr_no, int qty) throws SQLException {
        String query = "update books set qty = ? where sr_no = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, qty);
            ps.setInt(2, sr_no);
            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println("Quantity successfully updated.");
            else
                System.out.println("Quantity update failed.");
        }
    }
}
