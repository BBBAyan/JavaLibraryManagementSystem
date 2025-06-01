package com.dao;

import com.dto.BookingDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentDao {
    Scanner sc = new Scanner(System.in);

    public boolean getStudent_regNo(Connection conn, String regNo) throws SQLException {
        String query = "select * from students where reg_num = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, regNo);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    public int getStudentID_regNo(Connection conn, String regNo) throws SQLException {
        String query = "select * from students where reg_num = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, regNo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return 0;
    }

    public void getAllStudents(Connection conn) throws SQLException {
        String query = "select * from students";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            try (ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                    System.out.println("ID: " + rs.getInt(1) + ", Student name: " + rs.getString(2) +
                            ", Registration Number: " + rs.getString(3));
                }
            }
        }
    }

    public void insertStudent(Connection conn, String studentName, String regNo) throws SQLException {
        String query = "INSERT INTO students(std_name, reg_num) VALUES(?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, studentName);
            ps.setString(2, regNo);
            int rows_affected = ps.executeUpdate();
            if (rows_affected > 0)
                System.out.println("Inserting student was successful.");
            else
                System.out.println("Student insertion failed");
        }
    }

    public List<BookingDetails> getBookDetailsId(Connection conn, int stdId) throws SQLException {
        String query = "SELECT * FROM booking_details bd "
                + "INNER JOIN books b ON b.id = bd.book_id "
                + "WHERE bd.std_id = ?";

        List<BookingDetails> bookingDetails = new ArrayList<>();

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, stdId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                BookingDetails bookingDetail = new BookingDetails();
                bookingDetail.setAuthorName(resultSet.getString("author_name"));
                bookingDetail.setBook_id(resultSet.getInt("book_id"));
                bookingDetail.setBookName(resultSet.getString("name"));
                bookingDetail.setQty(resultSet.getInt("qty"));
                bookingDetail.setStd_id(resultSet.getInt("std_id"));
                bookingDetail.setSr_no(resultSet.getInt("sr_no"));
                bookingDetail.setId(resultSet.getInt("id"));

                bookingDetails.add(bookingDetail);
            }
        }
        return bookingDetails;
    }

    public void insertBookingDetail(Connection conn, int stdId, int bookId, int qty) throws SQLException {
        String query = "INSERT INTO booking_details(std_id, book_id, qty) VALUES(?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, stdId);
            ps.setInt(2, bookId);
            ps.setInt(3, qty);
            int rows_affected = ps.executeUpdate();
            if (rows_affected > 0)
                System.out.println("Inserting Booking Details was successful.");
            else
                System.out.println("Booking Detail insertion failed");
        }
    }

    public void deleteBookingDetail(Connection conn, int id) throws SQLException {
        String query = "delete from booking_details WHERE id = ? ";

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }
}
