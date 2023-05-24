package com.example;

import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class campusGui extends Application {
    private TextField campusNameField;
    private TextField addressField;
    private TextField directorNameField;
    private TextField directorEmailField;
    private Button addDepartmentButton;
    private ListView<String> departmentListView;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Create form components
        Label campusNameLabel = new Label("Campus Name:");
        TextField campusNameField = new TextField();

        Label addressLabel = new Label("Address:");
        addressField = new TextField();

        Label directorNameLabel = new Label("Director Name:");
        directorNameField = new TextField();

        Label directorEmailLabel = new Label("Director Email:");
        directorEmailField = new TextField();

        addDepartmentButton = new Button("Add Department");
        departmentListView = new ListView<>();

        // Set up layout
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        gridPane.add(campusNameLabel, 0, 0);
        gridPane.add(campusNameField, 1, 0);
        gridPane.add(addressLabel, 0, 1);
        gridPane.add(addressField, 1, 1);
        gridPane.add(directorNameLabel, 0, 2);
        gridPane.add(directorNameField, 1, 2);
        gridPane.add(directorEmailLabel, 0, 3);
        gridPane.add(directorEmailField, 1, 3);
        gridPane.add(addDepartmentButton, 0, 4);
        gridPane.add(departmentListView, 1, 4);

        // Set up event handling
        addDepartmentButton.setOnAction(e -> addDepartment());

        // Set up scene and stage
        Scene scene = new Scene(gridPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Campus Form");
        primaryStage.show();
    }

    private void addDepartment() {
        // Show a dialog to add department details
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Add Department");
        dialog.setHeaderText("Enter Department Name");

        // Set up dialog components
        GridPane dialogPane = new GridPane();
        dialogPane.setPadding(new Insets(10));
        dialogPane.setVgap(10);
        dialogPane.setHgap(10);

        TextField departmentNameField = new TextField();

        dialogPane.add(new Label("Department Name:"), 0, 0);
        dialogPane.add(departmentNameField, 1, 0);

        dialog.getDialogPane().setContent(dialogPane);

        // Set up result handling
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                return departmentNameField.getText();
            }
            return null;
        });

        // Show the dialog
        dialog.showAndWait().ifPresent(departmentName -> departmentListView.getItems().add(departmentName));
    }

    // Getters for the form data
    public String getCampusName() {
        return campusNameField.getText();
    }

    public String getAddress() {
        return addressField.getText();
    }

    public String getDirectorName() {
        return directorNameField.getText();
    }

    public String getDirectorEmail() {
        return directorEmailField.getText();
    }

    public String[] getDepartments() {
        return departmentListView.getItems().toArray(new String[0]);

    }
}
