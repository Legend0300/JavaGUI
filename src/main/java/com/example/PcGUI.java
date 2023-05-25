package com.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class PcGUI extends Application {
    private Pc pc;
    private TextField idField;
    private TextField nameField;
    private TextField speedField;
    private TextField ramField;
    private TextField diskSizeField;
    private TextField screenField;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("PC GUI");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(10);
        grid.setHgap(10);

        Label idLabel = new Label("ID:");
        idField = new TextField();
        grid.add(idLabel, 0, 0);
        grid.add(idField, 1, 0);

        Label nameLabel = new Label("Name:");
        nameField = new TextField();
        grid.add(nameLabel, 0, 1);
        grid.add(nameField, 1, 1);

        Label speedLabel = new Label("Speed:");
        speedField = new TextField();
        grid.add(speedLabel, 0, 2);
        grid.add(speedField, 1, 2);

        Label ramLabel = new Label("RAM:");
        ramField = new TextField();
        grid.add(ramLabel, 0, 3);
        grid.add(ramField, 1, 3);

        Label diskSizeLabel = new Label("Disk Size:");
        diskSizeField = new TextField();
        grid.add(diskSizeLabel, 0, 4);
        grid.add(diskSizeField, 1, 4);

        Label screenLabel = new Label("Screen:");
        screenField = new TextField();
        grid.add(screenLabel, 0, 5);
        grid.add(screenField, 1, 5);

        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> savePc());
        grid.add(saveButton, 0, 6);

        Button loadButton = new Button("Load");
        loadButton.setOnAction(e -> loadPc());
        grid.add(loadButton, 1, 6);

        Scene scene = new Scene(grid, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void savePc() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save PC");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PC File", "*.pc"));
        File file = fileChooser.showSaveDialog(null);
        if (file != null) {
            try {
                int id = Integer.parseInt(idField.getText());
                pc = new Pc(id, nameField.getText(), speedField.getText(),
                        ramField.getText(), diskSizeField.getText(), screenField.getText());
                pc.saveToFile(file.getPath());
            } catch (NumberFormatException e) {
                System.out.println("Invalid ID format");
            }
        }
    }

    private void loadPc() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load PC");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PC File", "*.pc"));
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            pc = Pc.loadFromFile(file.getPath());
            if (pc != null) {
                idField.setText(String.valueOf(pc.getId()));
                nameField.setText(pc.getName());
                speedField.setText(pc.getSpeed());
                ramField.setText(pc.getRAM());
                diskSizeField.setText(pc.getDiskSize());
                screenField.setText(pc.getScreen());
            }
        }
    }
}
