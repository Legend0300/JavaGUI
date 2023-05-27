package com.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class UniversityGUI extends Application {
    private University university;
    private TextField nameField;
    private ListView<String> campusListView;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("University GUI");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(10);
        grid.setHgap(10);

        Label nameLabel = new Label("University Name:");
        nameField = new TextField();
        grid.add(nameLabel, 0, 0);
        grid.add(nameField, 1, 0);

        Label campusLabel = new Label("Campuses:");
        campusListView = new ListView<>();
        VBox.setVgrow(campusListView, Priority.ALWAYS);
        campusListView.setPrefHeight(200);
        grid.add(campusLabel, 0, 1);
        grid.add(campusListView, 0, 2, 2, 1);

        Button addCampusButton = new Button("Add Campus");
        addCampusButton.setOnAction(e -> addCampus());
        grid.add(addCampusButton, 0, 3);

        Button removeCampusButton = new Button("Remove Campus");
        removeCampusButton.setOnAction(e -> removeCampus());
        grid.add(removeCampusButton, 1, 3);

        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> saveUniversity());
        grid.add(saveButton, 0, 4);

        Button loadButton = new Button("Load");
        loadButton.setOnAction(e -> loadUniversity());
        grid.add(loadButton, 1, 4);

        Scene scene = new Scene(grid, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addCampus() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add Campus");
        dialog.setHeaderText("Enter Campus Name");
        dialog.setContentText("Campus Name:");
        dialog.showAndWait().ifPresent(campusName -> {
//            Campus campus = new Campus(campusName, campusName);
//            university.addCampus(campus);
            campusListView.getItems().add(campusName);
        });
    }

    private void removeCampus() {
        int selectedIndex = campusListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            String campusName = campusListView.getSelectionModel().getSelectedItem();
            Campus campus = university.getCampus(campusName);
            university.removeCampus(campus);
            campusListView.getItems().remove(selectedIndex);
        }
    }

    private void saveUniversity() {
        String filename = "university.txt";
        University uni = new University(nameField.getText());
        ArrayList<String> campuses = new ArrayList<>(campusListView.getItems());
        for (String c : campuses) {
            Campus camp = new Campus(c);
            uni.addCampus(camp);
        }
        uni.saveToFile(filename);
    }

    private void loadUniversity() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load University");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("University File", "*.uni"));
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            university = University.loadFromFile(file.getPath());
            if (university != null) {
                nameField.setText(university.getName());
                campusListView.getItems().clear();
                for (Campus campus : university.getCampuses()) {
                    campusListView.getItems().add(campus.getCampusName());
                }
            }
        }
    }
    
}
