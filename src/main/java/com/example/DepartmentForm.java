package com.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class DepartmentForm extends Application {
    private TextField departmentNameField;
    private TextField hodNameField;
    private TextField hodEmailField;
    private Button addLabButton;
    private ListView<String> labListView;

    @Override
    public void start(Stage primaryStage) {
        // Create form components
        Label departmentNameLabel = new Label("Department Name:");
        departmentNameField = new TextField();

        Label hodNameLabel = new Label("HOD Name:");
        hodNameField = new TextField();

        Label hodEmailLabel = new Label("HOD Email:");
        hodEmailField = new TextField();

        addLabButton = new Button("Add Lab");
        labListView = new ListView<>();

        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> saveDepartmentInformation());

        // Set up layout
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        gridPane.add(departmentNameLabel, 0, 0);
        gridPane.add(departmentNameField, 1, 0);
        gridPane.add(hodNameLabel, 0, 1);
        gridPane.add(hodNameField, 1, 1);
        gridPane.add(hodEmailLabel, 0, 2);
        gridPane.add(hodEmailField, 1, 2);
        gridPane.add(addLabButton, 0, 3);
        gridPane.add(labListView, 1, 3);
        gridPane.add(saveButton, 0, 4);

        // Set up event handling
        addLabButton.setOnAction(e -> addLab());

        // Set up scene and stage
        Scene scene = new Scene(gridPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Department Form");
        primaryStage.show();
    }

    private void addLab() {
        // Create the dialog
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Add Lab");
        dialog.setHeaderText("Enter Lab Name");

        // Set up dialog components
        GridPane dialogPane = new GridPane();
        dialogPane.setPadding(new Insets(10));
        dialogPane.setVgap(10);
        dialogPane.setHgap(10);

        TextField labNameField = new TextField();

        dialogPane.add(new Label("Lab Name:"), 0, 0);
        dialogPane.add(labNameField, 1, 0);

        dialog.getDialogPane().setContent(dialogPane);

        // Add OK button
        ButtonType addButton = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);

        // Enable the OK button when the lab name is not empty
        Node addButtonNode = dialog.getDialogPane().lookupButton(addButton);
        addButtonNode.setDisable(true);
        labNameField.textProperty().addListener((observable, oldValue, newValue) ->
                addButtonNode.setDisable(newValue.trim().isEmpty()));

        // Set up result handling
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButton) {
                return labNameField.getText();
            }
            return null;
        });

        // Show the dialog
        dialog.showAndWait().ifPresent(labName -> labListView.getItems().add(labName));
    }

    private void saveDepartmentInformation() {
        String departmentName = getDepartmentName();
        String hodName = getHodName();
        String hodEmail = getHodEmail();
        String[] labs = getLabs();

        HOD hod = new HOD(hodName, hodEmail, hodEmail);
        Department department = new Department(hod, departmentName);

        for (String lab : labs) {
            Lab newLab = new Lab(null, lab, false);
            department.addLab(newLab);
        }

        String fileName = departmentName.replaceAll("\\s+", "") + ".txt";
        department.saveToFile(fileName);
    }

    // Getters for the form data
    public String getDepartmentName() {
        return departmentNameField.getText();
    }

    public String getHodName() {
        return hodNameField.getText();
    }

    public String getHodEmail() {
        return hodEmailField.getText();
    }

    public String[] getLabs() {
        return labListView.getItems().toArray(new String[0]);
    }

    // Main method to launch the application
    public static void main(String[] args) {
        launch(args);
    }
}
