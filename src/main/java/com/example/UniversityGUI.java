package com.example;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
        grid.add(campusListView, 1, 1);

        Button addCampusButton = new Button("Add Campus");
        addCampusButton.setOnAction(e -> addCampus());
        grid.add(addCampusButton, 0, 2);

        Button removeCampusButton = new Button("Remove Campus");
        removeCampusButton.setOnAction(e -> removeCampus());
        grid.add(removeCampusButton, 1, 2);

        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> saveUniversity());
        grid.add(saveButton, 0, 3);

        Button loadButton = new Button("Load");
        loadButton.setOnAction(e -> loadUniversity());
        grid.add(loadButton, 1, 3);

        Button prevButton = new Button("Previous");
        prevButton.setOnAction(e -> displayPreviousUniversity());
        grid.add(prevButton, 0, 4);

        Button nextButton = new Button("Next");
        nextButton.setOnAction(e -> displayNextUniversity());
        grid.add(nextButton, 1, 4);

        Button addUniversityButton = new Button("Add University");
        addUniversityButton.setOnAction(e -> addUniversity());
        grid.add(addUniversityButton, 0, 5);

        Button deleteUniversityButton = new Button("Delete University");
        deleteUniversityButton.setOnAction(e -> deleteUniversity());
        grid.add(deleteUniversityButton, 1, 5);

        Scene scene = new Scene(grid, 400, 400);
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
        universityList = University.loadAll("universities.ser");
        if (!universityList.isEmpty()) {
            currentUniversityIndex = 0;
            displayUniversity(universityList.get(currentUniversityIndex));
        }
    }

    private void addUniversity() {
        Dialog<University> dialog = new Dialog<>();
        dialog.setTitle("Add University");
        dialog.setHeaderText("Enter University Details");

        ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField nameField = new TextField();
        nameField.setPromptText("University Name");

        grid.add(new Label("Name:"), 0, 0);
        grid.add(nameField, 1, 0);

        ListView<String> campusListView = new ListView<>();
        VBox.setVgrow(campusListView, Priority.ALWAYS);
        campusListView.setPrefHeight(150);
        grid.add(new Label("Campuses:"), 0, 1);
        grid.add(campusListView, 1, 1);

        Button addCampusButton = new Button("Add Campus");
        addCampusButton.setOnAction(e -> {
            TextInputDialog campusDialog = new TextInputDialog();
            campusDialog.setTitle("Add Campus");
            campusDialog.setHeaderText("Enter Campus Name");
            campusDialog.setContentText("Campus Name:");
            campusDialog.showAndWait().ifPresent(campusName -> {
                campusListView.getItems().add(campusName);
            });
        });

        grid.add(addCampusButton, 1, 2);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                String name = nameField.getText().trim();
                if (!name.isEmpty()) {
                    University university = new University(name);
                    university.getCampuses().addAll(campusListView.getItems());
                    return university;
                }
            }
            return null;
        });

        dialog.showAndWait().ifPresent(university -> {
            universityList.add(university);
            currentUniversityIndex = universityList.size() - 1;
            displayUniversity(university);
        });
    }

    private void deleteUniversity() {
        if (currentUniversityIndex != -1) {
            universityList.remove(currentUniversityIndex);
            if (universityList.isEmpty()) {
                currentUniversityIndex = -1;
                nameField.clear();
                campusListView.getItems().clear();
            } else if (currentUniversityIndex >= universityList.size()) {
                currentUniversityIndex = universityList.size() - 1;
                displayUniversity(universityList.get(currentUniversityIndex));
            } else {
                displayUniversity(universityList.get(currentUniversityIndex));
            }
            University.saveAll(universityList, "universities.ser");
        }
    }

    private void displayUniversity(University university) {
        nameField.setText(university.getName());
        ObservableList<Campus> campusNames = FXCollections.observableArrayList(
                university.getCampuses());
        campusListView.setItems(campusNames);
    }

    private void displayNextUniversity() {
        if (!universityList.isEmpty()) {
            currentUniversityIndex = (currentUniversityIndex + 1) % universityList.size();
            University university = universityList.get(currentUniversityIndex);
            displayUniversity(university);
        }
    }

    private void displayPreviousUniversity() {
        if (!universityList.isEmpty()) {
            currentUniversityIndex = (currentUniversityIndex - 1 + universityList.size()) % universityList.size();
            University university = universityList.get(currentUniversityIndex);
            displayUniversity(university);
        }
    }
}
