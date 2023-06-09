package com.example;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class LabStaffGUI extends Application {
    private LabStaff labStaff;
    private TextField usernameField;
    private TextField gradeField;
    private TextField passwordField;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Lab Staff GUI");

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

        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> saveLabStaff());
        grid.add(saveButton, 0, 3);

        Button loadButton = new Button("Load");
        loadButton.setOnAction(e -> loadLabStaff());
        grid.add(loadButton, 1, 3);

        // Create the "Menu" button
        Button menuButton = new Button(" < ");
        menuButton.setPrefSize(40, 40);
        menuButton.setOnAction(e -> {
            Menu menu = new Menu();
            menu.start(new Stage());
            primaryStage.close();
        });

        // Create an HBox to hold the buttons
        HBox buttonsBox = new HBox(10);
        buttonsBox.setAlignment(Pos.CENTER);
        buttonsBox.getChildren().addAll(saveButton, loadButton);

        // Create a StackPane to hold the "Menu" button
        StackPane menuButtonPane = new StackPane(menuButton);
        menuButtonPane.setAlignment(Pos.TOP_LEFT);
        menuButtonPane.setPadding(new Insets(10));

        // Create a BorderPane to hold the main content and the "Menu" button
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(grid);
        borderPane.setBottom(buttonsBox);
        borderPane.setTop(menuButtonPane);

        Scene scene = new Scene(borderPane, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void saveLabStaff() {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Lab Staff");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Lab Staff File", "*.staff"));
        File file = fileChooser.showSaveDialog(null);
        if (file != null) {
            labStaff = new LabStaff(usernameField.getText(), gradeField.getText(), passwordField.getText());
            labStaff.saveToFile(file.getPath());
        }
    }

    private void loadLabStaff() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load Lab Staff");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Lab Staff File", "*.staff"));
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            labStaff = LabStaff.loadFromFile(file.getPath());
            if (labStaff != null) {
                usernameField.setText(labStaff.getUsername());
                gradeField.setText(labStaff.getGrade());
                passwordField.setText(labStaff.getPassword());
            }
        }
    }
}
