package com.example.homework.Repository;

import com.example.homework.Models.Lesson;
import java.sql.*;

public class LessonRepository {
    private final String URL = "jdbc:mysql://127.0.0.1:3306/school";
    private final String USER = "root";
    private final String PASSWORD = "5507";
    private Connection conn = null;
    private Statement stm = null;
    private ResultSet rs = null;

    private void createTableIfNotExists() throws SQLException {
        conn = DriverManager.getConnection(URL, USER, PASSWORD);
        String query = "CREATE TABLE IF NOT EXISTS lessons (" +
                "lesson_id INT PRIMARY KEY, " +
                "lesson_name VARCHAR(255) NOT NULL, " +
                "credits INT NOT NULL);";
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(query);
        conn.close();
    }

    public LessonRepository() throws SQLException {
        createTableIfNotExists();
    }

    public Lesson getLessonById(int lessonId) throws SQLException {
        conn = DriverManager.getConnection(URL, USER, PASSWORD);
        String query = "SELECT * FROM lessons WHERE lesson_id = " + lessonId + ";";
        stm = conn.createStatement();
        rs = stm.executeQuery(query);
        Lesson l = new Lesson();
        while (rs.next()) {
            l.setId(rs.getInt("lesson_id"));
            l.setName(rs.getString("lesson_name"));
            l.setCredits(rs.getInt("credits"));
        }
        conn.close();
        return l;
    }

    public void saveLesson(Lesson l) throws SQLException {
        conn = DriverManager.getConnection(URL, USER, PASSWORD);
        stm = conn.createStatement();
        String query = "INSERT INTO lessons VALUES (" + l.getId() + ", '" + l.getName() + "', " + l.getCredits() + ");";
        stm.executeUpdate(query);
        conn.close();
    }

    public int updateLesson(int lessonId, String name, int credits) throws SQLException {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement("UPDATE lessons SET lesson_name = ?, credits = ? WHERE lesson_id = ?")) {

            pstmt.setString(1, name);
            pstmt.setInt(2, credits);
            pstmt.setInt(3, lessonId);

            return pstmt.executeUpdate(); // Güncellenen satır sayısını döndür
        }
    }

    public int deleteLesson(int lessonId) throws SQLException {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM lessons WHERE lesson_id = ?")) {

            pstmt.setInt(1, lessonId);
            return pstmt.executeUpdate(); // Silinen satır sayısını döndür
        }
    }

}
