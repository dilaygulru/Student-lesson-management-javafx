package com.example.homework.Repository;

import com.example.homework.Models.Student;
import java.sql.*;

public class StudentRepository {
    private final String URL = "jdbc:mysql://127.0.0.1:3306/school";
    private final String USER = "root";
    private final String PASSWORD = "5507";
    private Connection conn = null;
    private Statement stm = null;
    private ResultSet rs = null;

    private void createTableIfNotExists() throws SQLException {
        conn = DriverManager.getConnection(URL, USER, PASSWORD);
        String query = "CREATE TABLE IF NOT EXISTS students (" +
                "student_id INT PRIMARY KEY, " +  // AUTO_INCREMENT KALDIRILDI
                "student_name VARCHAR(255) NOT NULL, " +
                "student_surname VARCHAR(255) NOT NULL);";
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(query);
        conn.close();
    }

    public StudentRepository() throws SQLException {
        createTableIfNotExists();
    }

    public Student getStudentById(int sid) throws SQLException {
        conn = DriverManager.getConnection(URL, USER, PASSWORD);
        String query = "SELECT * FROM students WHERE student_id = " + sid + ";";
        stm = conn.createStatement();
        rs = stm.executeQuery(query);
        Student s = new Student();

        while (rs.next()) {
            s.setId(rs.getInt("student_id"));
            s.setName(rs.getString("student_name"));
            s.setSurname(rs.getString("student_surname"));
        }

        conn.close();
        return s;
    }

    public void saveStudent(Student s) throws SQLException {
        conn = DriverManager.getConnection(URL, USER, PASSWORD);
        stm = conn.createStatement();
        String query = "INSERT INTO students VALUES (" + s.getId() + ", '" + s.getName() + "', '" + s.getSurname() + "');";
        stm.executeUpdate(query);
        conn.close();
    }

    public int updateStudent(int sid, String name, String surname) throws SQLException {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement("UPDATE students SET student_name = ?, student_surname = ? WHERE student_id = ?")) {

            pstmt.setString(1, name);
            pstmt.setString(2, surname);
            pstmt.setInt(3, sid);

            return pstmt.executeUpdate(); // Güncellenen satır sayısını döndür
        }
    }

    public int deleteStudent(int sid) throws SQLException {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM students WHERE student_id = ?")) {

            pstmt.setInt(1, sid);
            return pstmt.executeUpdate(); // Silinen satır sayısını döndür
        }
    }

}
