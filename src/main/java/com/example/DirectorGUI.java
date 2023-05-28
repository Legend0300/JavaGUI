//package com.example;
//
//import javafx.application.Application;
//import javafx.geometry.Insets;
//import javafx.scene.Node;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.layout.GridPane;
//import javafx.stage.FileChooser;
//import javafx.stage.Stage;
//
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class DirectorGUI extends Application {
//    private Director director;
//    private TextField usernameField;
//    private TextField gradeField;
//    private TextField passwordField;
//
//    List<Employee> directors = new ArrayList<>();
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//
//    @Override
//    public void start(Stage primaryStage) {
//        primaryStage.setTitle("Director GUI");
//
//        GridPane grid = new GridPane();
//        grid.setPadding(new Insets(10));
//        grid.setVgap(10);
//        grid.setHgap(10);
//
//        Label usernameLabel = new Label("Director Username:");
//        usernameField = new TextField();
//        grid.add(usernameLabel, 0, 0);
//        grid.add(usernameField, 1, 0);
//
//        Label gradeLabel = new Label("Director Grade:");
//        gradeField = new TextField();
//        grid.add(gradeLabel, 0, 1);
//        grid.add(gradeField, 1, 1);
//
//        Label passwordLabel = new Label("Director Password:");
//        passwordField = new TextField();
//        grid.add(passwordLabel, 0, 2);
//        grid.add(passwordField, 1, 2);
//
////        Label directorUsernameLabel = new Label("Director Username:");
////        directorUsernameField = new TextField();
////        grid.add(directorUsernameLabel, 0, 3);
////        grid.add(directorUsernameField, 1, 3);
////
////        Label directorGradeLabel = new Label("Director Grade:");
////        directorGradeField = new TextField();
////        grid.add(directorGradeLabel, 0, 4);
////        grid.add(directorGradeField, 1, 4);
////
////        Label directorPasswordLabel = new Label("Director Password:");
////        directorPasswordField = new TextField();
////        grid.add(directorPasswordLabel, 0, 5);
////        grid.add(directorPasswordField, 1, 5);
//
//        Button saveButton = new Button("Save");
//        saveButton.setOnAction(e -> saveDirector());
//        grid.add(saveButton, 0, 6);
//
//        Button loadButton = new Button("Search");
//        loadButton.setOnAction(e -> loadDirector());
//        grid.add(loadButton, 1, 6);
//
//        Scene scene = new Scene(grid, 300, 250);
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//
//    private void saveDirector() {
//        String username = usernameField.getText();
//        String grade = gradeField.getText();
//        String password = passwordField.getText();
//
//
//        Director director = new Director(username, grade, password);
//        //director.setDirectorDetails(new Director(username, grade, password));
//        directors.add(director);
//
//
//
//
//
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.setTitle("Save Director");
//        File file = fileChooser.showSaveDialog(null);
//        if (file != null) {
//            director.saveToFile(file.getPath());
//        }
//    }
//
//    private void loadDirector() {
//        Dialog<String> dialog = new Dialog<>();
//        dialog.setTitle("Search Director");
//        dialog.setHeaderText("Enter DIrector Name");
//
//        // Set up dialog components
//        GridPane dialogPane = new GridPane();
//        dialogPane.setPadding(new Insets(10));
//        dialogPane.setVgap(10);
//        dialogPane.setHgap(10);
//
//        TextField directorNameField = new TextField();
//
//        dialogPane.add(new Label("Department Name:"), 0, 0);
//        dialogPane.add(directorNameField, 1, 0);
//
//        dialog.getDialogPane().setContent(dialogPane);
//
//        // Add OK button
//        ButtonType addButton = new ButtonType("Search", ButtonBar.ButtonData.OK_DONE);
//        dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);
//
//        // Enable the OK button when the department name is not empty
//        Node addButtonNode = dialog.getDialogPane().lookupButton(addButton);
//        addButtonNode.setDisable(true);
//        directorNameField.textProperty().addListener((observable, oldValue, newValue) ->
//                addButtonNode.setDisable(newValue.trim().isEmpty()));
//
//        // Set up result handling
//        dialog.setResultConverter(dialogButton -> {
//            for(Employee d:directors) {
//                if (directorNameField.getText().equals(d.getUsername()))
//                {
//                     usernameField.setText(d.getUsername());
//                     gradeField.setText(d.getGrade());
//                     passwordField.setText(d.getPassword());
//                }
//            }
//            return null;
//        });
////        String username = usernameField.getText();
////        for(Employee d : directors){
////            if(d instanceof Director){
////                if(username.equals(d.getUsername())){
////
////                }
////            }
////        }
////        fileChooser.setTitle("Load Director");
////        File file = fileChooser.showOpenDialog(null);
////        if (file != null) {
////            director = Director.loadFromFile(file.getPath());
////            if (director != null) {
////                usernameField.setText(director.getUsername());
////                gradeField.setText(director.getGrade());
////                passwordField.setText(director.getPassword());
////                directorUsernameField.setText(director.getDirectorDetails().getUsername());
////                directorGradeField.setText(director.getDirectorDetails().getGrade());
////                directorPasswordField.setText(director.getDirectorDetails().getPassword());
////            }
////        }
//    }
//}
