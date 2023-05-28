package com.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class LoginGUI extends Application {

    private TextField usernameTextField;
    private PasswordField passwordField;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Login");

        // Create a GridPane layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Add title label
        Label title = new Label("Login");
        title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(title, 0, 0, 2, 1);

        // Add username label and text field
        Label usernameLabel = new Label("Username:");
        grid.add(usernameLabel, 0, 1);

        usernameTextField = new TextField();
        grid.add(usernameTextField, 1, 1);

        // Add password label and password field
        Label passwordLabel = new Label("Password:");
        grid.add(passwordLabel, 0, 2);

        passwordField = new PasswordField();
        grid.add(passwordField, 1, 2);

        // Add login button
        Button loginButton = new Button("Log In");
        grid.add(loginButton, 1, 3);

        // Set action for login button
        loginButton.setOnAction(e -> {
            String username = usernameTextField.getText();
            String password = passwordField.getText();

            // Validate the username and password
            if (isValidCredentials(username, password)) {
                // Display a success message
                System.out.println("Login successful!");

                // Start the Functionalities
                Functionalities functionalities = new Functionalities();
                functionalities.start(new Stage());

                // Close the login window
                primaryStage.close();
            } else {
                // Display an error message
                System.out.println("Invalid username or password. Please try again.");
            }
        });

        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Dummy method to validate credentials
    private boolean isValidCredentials(String username, String password) {
        // Replace with your own logic to validate credentials
        return username.equals("admin") && password.equals("password");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
