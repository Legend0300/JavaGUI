package com.example;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
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
        grid.add(campusListView, 0, 2, 2, 1);

        Button addCampusButton = new Button("Add Campus");
        addCampusButton.setOnAction(e -> addCampus());

        Button removeCampusButton = new Button("Remove Campus");
        removeCampusButton.setOnAction(e -> removeCampus());

        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> saveUniversity());

        Button loadButton = new Button("Load");
        loadButton.setOnAction(e -> loadUniversity());

        // Create an HBox to hold the buttons
        HBox buttonBox = new HBox(loadButton, saveButton, addCampusButton, removeCampusButton);
        buttonBox.setSpacing(10);
        buttonBox.setPadding(new Insets(10));

        // Create a VBox to hold the main content (grid) and the buttons
        VBox vbox = new VBox(grid, buttonBox);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));

        // Set the VBox as the center of the BorderPane
        borderPane.setCenter(vbox);

        Scene scene = new Scene(borderPane, 400, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
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
            System.out.println("University loaded from file: " + file.getName());
        }
    }

//    private Scene createMenuScene() {
//        GridPane menuGrid = new GridPane();
//        menuGrid.setPadding(new Insets(10));
//        menuGrid.setVgap(10);
//        menuGrid.setHgap(10);
//
//        Button menuButton = new Button("Menu");
//        menuButton.setOnAction(e -> {
//            // Implement your menu button functionality here
//        });
//
//        menuGrid.add(menuButton, 0, 0);
//
//        return new Scene(menuGrid, 300, 200);
//    }
    private Scene createMenuScene() {
    Menu menu = new Menu();
    menu.start(new Stage());
        return null;
    }
}
