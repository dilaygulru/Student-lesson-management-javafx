package com.example.homework.Controllers;

import com.example.homework.HelloApplication;
import com.example.homework.Models.Student;
import com.example.homework.Repository.StudentRepository;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class StudentController {
    @FXML private Label labelTitle;
    @FXML private TextField txtId, txtName, txtSurname;
    @FXML private Button btnFetch, btnCreate, btnUpdate, btnDelete, btnLesson;

    private final StudentRepository studentRepository;

    public StudentController() throws SQLException {
        studentRepository = new StudentRepository();
    }



    @FXML
    void onLessonButtonClick() throws IOException {
        Stage stage = (Stage) txtId.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("Lesson.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.setTitle("Lesson Management");
        stage.show();
    }

    @FXML
    void onFetchButtonClick() {
        try {
            Student s = studentRepository.getStudentById(Integer.parseInt(txtId.getText()));
            if (s.getId() == 0) {
                showAlert("No such record!");
                txtId.setText("");
                txtId.requestFocus();
            } else {
                txtName.setText(s.getName());
                txtSurname.setText(s.getSurname());
            }
        } catch (SQLException e) {
            showAlert("Database error: " + e.getMessage());
        }
    }

    @FXML
    void onCreateButtonClick() {
        try {
            Student s = new Student(Integer.parseInt(txtId.getText()), txtName.getText(), txtSurname.getText());
            studentRepository.saveStudent(s);
            showAlert("Student created successfully!");
        } catch (SQLException e) {
            showAlert("Database error: " + e.getMessage());
        }
    }

    @FXML
    void onUpdateButtonClick() {
        try {
            int rowsAffected = studentRepository.updateStudent(
                    Integer.parseInt(txtId.getText()),
                    txtName.getText(),
                    txtSurname.getText()
            );
            if (rowsAffected > 0) {
                showAlert("Student updated successfully!");
            } else {
                showAlert("No student found with this ID!");
            }
        } catch (SQLException e) {
            showAlert("Database error: " + e.getMessage());
        }
    }

    @FXML
    void onDeleteButtonClick() {
        try {
            int rowsAffected = studentRepository.deleteStudent(Integer.parseInt(txtId.getText()));
            if (rowsAffected > 0) {
                showAlert("Student deleted successfully!");
                txtId.setText("");
                txtName.setText("");
                txtSurname.setText("");
                txtId.requestFocus();
            } else {
                showAlert("No student found with this ID!");
            }
        } catch (SQLException e) {
            showAlert("Database error: " + e.getMessage());
        }
    }


    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.CLOSE);
        alert.showAndWait();
    }
}
