package com.service;

import com.dao.BookDao;
import com.dao.StudentDao;
import com.dto.Book;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class StudentService {
    Scanner sc = new Scanner(System.in);

    public void addStudent(Connection conn) throws SQLException {
        System.out.println("Enter Student Name:");
        String Name = sc.nextLine();
        System.out.println("Enter Registration Number:");
        String regNo = sc.nextLine();
        StudentDao dao = new StudentDao();
        boolean isStdExist = dao.getStudent_regNo(conn, regNo);
        if (isStdExist){
            System.out.println("Such student already exists.");
            return;
        }
        dao.insertStudent(conn, Name, regNo);
    }

    public void displayAllStudents(Connection conn) throws SQLException {
        StudentDao dao = new StudentDao();
        dao.getAllStudents(conn);
    }
}
