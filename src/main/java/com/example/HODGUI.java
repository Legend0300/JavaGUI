package com.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class HODGUI extends Application {
    private HOD hod;
    private TextField usernameField;
    private TextField gradeField;
    private TextField passwordField;
    private TextField hodUsernameField;
    private TextField hodGradeField;
    private TextField hodPasswordField;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("HOD GUI");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(10);
        grid.setHgap(10);

        Label usernameLabel = new Label("Username:");
        usernameField = new TextField();
        grid.add(usernameLabel, 0, 0);
        grid.add(usernameField, 1, 0);

        Label gradeLabel = new Label("Grade:");
        gradeField = new TextField();
        grid.add(gradeLabel, 0, 1);
        grid.add(gradeField, 1, 1);

        Label passwordLabel = new Label("Password:");
        passwordField = new TextField();
        grid.add(passwordLabel, 0, 2);
        grid.add(passwordField, 1, 2);

        Label hodUsernameLabel = new Label("HOD Username:");
        hodUsernameField = new TextField();
        grid.add(hodUsernameLabel, 0, 3);
        grid.add(hodUsernameField, 1, 3);

        Label hodGradeLabel = new Label("HOD Grade:");
        hodGradeField = new TextField();
        grid.add(hodGradeLabel, 0, 4);
        grid.add(hodGradeField, 1, 4);

        Label hodPasswordLabel = new Label("HOD Password:");
        hodPasswordField = new TextField();
        grid.add(hodPasswordLabel, 0, 5);
        grid.add(hodPasswordField, 1, 5);

        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> saveHOD());
        grid.add(saveButton, 0, 6);

        Button loadButton = new Button("Load");
        loadButton.setOnAction(e -> loadHOD());
        grid.add(loadButton, 1, 6);

        Scene scene = new Scene(grid, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void saveHOD() {
        String username = usernameField.getText();
        String grade = gradeField.getText();
        String password = passwordField.getText();
        String hodUsername = hodUsernameField.getText();
        String hodGrade = hodGradeField.getText();
        String hodPassword = hodPasswordField.getText();

        hod = new HOD(username, grade, password);

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save HOD");
        File file = fileChooser.showSaveDialog(null);
        if (file != null) {
            hod.saveToFile(file.getPath());
        }
    }

    private void loadHOD() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load HOD");
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            hod = HOD.loadFromFile(file.getPath());
            if (hod != null) {
                usernameField.setText(hod.getUsername());
                gradeField.setText(hod.getGrade());
                passwordField.setText(hod.getPassword());
                hodUsernameField.setText(hod.getHodDetails().getUsername());
                hodGradeField.setText(hod.getHodDetails().getGrade());
                hodPasswordField.setText(hod.getHodDetails().getPassword());
            }
        }
    }
}
