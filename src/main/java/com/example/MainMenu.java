package com.example;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenu extends Application{


        @Override
        public void start(Stage primaryStage) {
            // Create buttons for department form and other options
            Button departmentFormButton = new Button("Department Form");
            // Add event handler to open the department form
            departmentFormButton.setOnAction(e -> {
                DepartmentForm departmentForm = new DepartmentForm();
                departmentForm.start(new Stage());
                primaryStage.close();
            });

            // Create a vertical layout to hold the buttons
            VBox vbox = new VBox(10);
            vbox.setAlignment(Pos.CENTER);
            vbox.getChildren().addAll(departmentFormButton);

            // Set up scene and stage
            Scene scene = new Scene(vbox, 200, 200);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Menu");
            primaryStage.show();
        }

        // Main method to launch the application
        public static void main(String[] args) {
            launch(args);
        }
    }


