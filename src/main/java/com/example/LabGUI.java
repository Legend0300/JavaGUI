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

public class LabGUI extends Application {
    private Lab lab;
    private TextField inchargeField;
    private TextField labNameField;
    private CheckBox projectorCheckBox;
    private ListView<Pc> pcListView;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Lab GUI");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(10);
        grid.setHgap(10);

        Label inchargeLabel = new Label("Incharge:");
        inchargeField = new TextField();
        grid.add(inchargeLabel, 0, 0);
        grid.add(inchargeField, 1, 0);

        Label labNameLabel = new Label("Lab Name:");
        labNameField = new TextField();
        grid.add(labNameLabel, 0, 1);
        grid.add(labNameField, 1, 1);

        Label projectorLabel = new Label("Has Projector:");
        projectorCheckBox = new CheckBox();
        grid.add(projectorLabel, 0, 2);
        grid.add(projectorCheckBox, 1, 2);

        pcListView = new ListView<>();
        pcListView.setPrefHeight(200);
        grid.add(pcListView, 0, 3, 2, 1);

        Button addButton = new Button("Add PC");
        addButton.setOnAction(e -> addPc());
        grid.add(addButton, 0, 4);

        Button removeButton = new Button("Remove PC");
        removeButton.setOnAction(e -> removePc());
        grid.add(removeButton, 1, 4);

        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> saveLab());
        grid.add(saveButton, 0, 5);

        Button loadButton = new Button("Load");
        loadButton.setOnAction(e -> loadLab());
        grid.add(loadButton, 1, 5);

        // Create the "Menu" button
        Button menuButton = new Button(" < ");
        menuButton.setPrefSize(40, 40);
        menuButton.setOnAction(e -> {
            Menu menu = new Menu();
            menu.start(new Stage());
            primaryStage.close();
        });

        // Create a StackPane to hold the "Menu" button
        StackPane menuButtonPane = new StackPane(menuButton);
        menuButtonPane.setAlignment(Pos.TOP_LEFT);
        menuButtonPane.setPadding(new Insets(10));

        // Create an HBox to hold the bottom four buttons
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(10));
        buttonBox.getChildren().addAll(addButton, removeButton, saveButton, loadButton);

        // Create a VBox to hold the main content
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(10));
        vbox.getChildren().addAll(grid, buttonBox);

        // Create a BorderPane to hold the main content and the "Menu" button
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(vbox);
        borderPane.setTop(menuButtonPane);

        Scene scene = new Scene(borderPane, 300, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void addPc() {
        Dialog<Pc> dialog = new Dialog<>();
        dialog.setTitle("Add PC");
        dialog.setHeaderText("Enter PC Details");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(10);
        grid.setHgap(10);

        TextField idField = new TextField();
        idField.setPromptText("ID");
        TextField nameField = new TextField();
        nameField.setPromptText("Name");
        TextField speedField = new TextField();
        speedField.setPromptText("Speed");
        TextField ramField = new TextField();
        ramField.setPromptText("RAM");
        TextField diskSizeField = new TextField();
        diskSizeField.setPromptText("Disk Size");
        TextField screenField = new TextField();
        screenField.setPromptText("Screen");

        grid.add(new Label("ID:"), 0, 0);
        grid.add(idField, 1, 0);
        grid.add(new Label("Name:"), 0, 1);
        grid.add(nameField, 1, 1);
        grid.add(new Label("Speed:"), 0, 2);
        grid.add(speedField, 1, 2);
        grid.add(new Label("RAM:"), 0, 3);
        grid.add(ramField, 1, 3);
        grid.add(new Label("Disk Size:"), 0, 4);
        grid.add(diskSizeField, 1, 4);
        grid.add(new Label("Screen:"), 0, 5);
        grid.add(screenField, 1, 5);

        dialog.getDialogPane().setContent(grid);

        ButtonType addButton = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);

        dialog.setResultConverter(buttonType -> {
            if (buttonType == addButton) {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                String speed = speedField.getText();
                String ram = ramField.getText();
                String diskSize = diskSizeField.getText();
                String screen = screenField.getText();
                return new Pc(id, name, speed, ram, diskSize, screen);
            }
            return null;
        });

        dialog.showAndWait().ifPresent(pc -> {
          //  lab.addPc(pc);
            pcListView.getItems().add(pc);
        });
    }

    private void removePc() {
        Pc selectedPc = pcListView.getSelectionModel().getSelectedItem();
        if (selectedPc != null) {
            //lab.removePc(selectedPc);
            pcListView.getItems().remove(selectedPc);
        }
    }

    private void saveLab() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Lab");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Lab File", "*.lab"));
        File file = fileChooser.showSaveDialog(null);
        if (file != null) {
            lab.setIncharge(new LabStaff(null, null, null));
            lab.setLabName(labNameField.getText());
            lab.setHasProjector(projectorCheckBox.isSelected());
            lab.saveToFile(file.getPath());
        }
    }

    private void loadLab() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load Lab");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Lab File", "*.lab"));
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            lab = Lab.loadFromFile(file.getPath());
            if (lab != null) {
                inchargeField.setText(lab.getIncharge().getName());
                labNameField.setText(lab.getLabName());
                projectorCheckBox.setSelected(lab.isHasProjector());
                pcListView.getItems().setAll(lab.getPcs());
            }
        }
    }
}
