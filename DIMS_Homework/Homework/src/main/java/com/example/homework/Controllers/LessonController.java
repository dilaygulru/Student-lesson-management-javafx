package com.example.homework.Controllers;

import com.example.homework.HelloApplication;
import com.example.homework.Models.Lesson;
import com.example.homework.Repository.LessonRepository;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LessonController {
    @FXML private TextField txtLessonId, txtLessonName, txtCredits;
    @FXML private Button btnFetchLesson, btnCreateLesson, btnUpdateLesson, btnDeleteLesson, btnStudent;

    private final LessonRepository lessonRepository;

    public LessonController() throws SQLException {
        lessonRepository = new LessonRepository();
    }



    @FXML
    void onStudentButtonClick() throws IOException {
        Stage stage = (Stage) txtLessonId.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("student.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.setTitle("Student Management");
        stage.show();
    }

    @FXML
    void onFetchLessonClick() {
        try {
            Lesson l = lessonRepository.getLessonById(Integer.parseInt(txtLessonId.getText()));

            if (l.getId() == 0 || l.getName() == null) {
                showAlert("No such lesson record!");
                txtLessonId.setText("");
                txtLessonId.requestFocus();
            } else {
                txtLessonName.setText(l.getName());
                txtCredits.setText(String.valueOf(l.getCredits()));
            }
        } catch (SQLException e) {
            showAlert("Database error: " + e.getMessage());
        }
    }

    @FXML
    void onCreateLessonClick() {
        try {
            int lessonId = Integer.parseInt(txtLessonId.getText().trim());
            String lessonName = txtLessonName.getText().trim();
            int credits = Integer.parseInt(txtCredits.getText().trim());

            Lesson lesson = new Lesson(lessonId, lessonName, credits);
            lessonRepository.saveLesson(lesson);
            showAlert("Lesson created successfully!");

            clearFields();
        } catch (SQLException e) {
            showAlert("Database error: " + e.getMessage());
        } catch (NumberFormatException e) {
            showAlert("Invalid input! Please enter correct values.");
        }
    }

    @FXML
    void onUpdateLessonClick() {
        try {
            int lessonId = Integer.parseInt(txtLessonId.getText().trim());
            String lessonName = txtLessonName.getText().trim();
            int credits = Integer.parseInt(txtCredits.getText().trim());

            int rowsAffected = lessonRepository.updateLesson(lessonId, lessonName, credits);
            if (rowsAffected > 0) {
                showAlert("Lesson updated successfully!");
            } else {
                showAlert("No lesson found with this ID!");
            }
        } catch (SQLException e) {
            showAlert("Database error: " + e.getMessage());
        } catch (NumberFormatException e) {
            showAlert("Invalid input! Please enter correct values.");
        }
    }

    @FXML
    void onDeleteLessonClick() {
        try {
            int rowsAffected = lessonRepository.deleteLesson(Integer.parseInt(txtLessonId.getText()));
            if (rowsAffected > 0) {
                showAlert("Lesson deleted successfully!");
                clearFields();
            } else {
                showAlert("No lesson found with this ID!");
            }
        } catch (SQLException e) {
            showAlert("Database error: " + e.getMessage());
        } catch (NumberFormatException e) {
            showAlert("Invalid input! Please enter a valid Lesson ID.");
        }
    }


    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.CLOSE);
        alert.showAndWait();
    }

    private void clearFields() {
        txtLessonId.setText("");
        txtLessonName.setText("");
        txtCredits.setText("");
        txtLessonId.requestFocus();
    }
}
