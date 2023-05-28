package com.example;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class UniversityGUI extends Application {
    private List<University> universityList = new ArrayList<>();
    private TextField nameField;
    private ListView<String> campusListView;
    private int currentUniversityIndex = -1;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        primaryStage.setTitle("University GUI");

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(10));

        // Create the back button
        Button backButton = new Button("<");
        backButton.setOnAction(e -> primaryStage.setScene(createMenuScene()));

        // Create a VBox to hold the back button
        VBox topVBox = new VBox(backButton);
        topVBox.setPadding(new Insets(10));
        borderPane.setTop(topVBox);

        GridPane grid = new GridPane();
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
        grid.add(campusListView, 1, 1);

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

        loadUniversity();
    }

    private void addCampus() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add Campus");
        dialog.setHeaderText("Enter Campus Name");
        dialog.setContentText("Campus Name:");
        dialog.showAndWait().ifPresent(campusName -> {
            campusListView.getItems().add(campusName);
        });
    }

    private void removeCampus() {
        int selectedIndex = campusListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            campusListView.getItems().remove(selectedIndex);
        }
    }

    private void saveUniversity() {
        if (currentUniversityIndex != -1) {
            University university = universityList.get(currentUniversityIndex);
            university.setName(nameField.getText());
            university.getCampuses().clear();
            university.getCampuses().addAll(campusListView.getItems());
        } else {
            University university = new University(nameField.getText());
            university.getCampuses().addAll(campusListView.getItems());
            universityList.add(university);
            currentUniversityIndex = universityList.size() - 1;
        }
        University.saveAll(universityList, "universities.ser");
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
