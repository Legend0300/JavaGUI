package com.example;

import java.io.*;
import java.util.Scanner;

public abstract class Employee implements Serializable {
    private String username;
    private String grade;
    private String password;

    public Employee(String username, String grade, String password) {
        this.username = username;
        this.grade = grade;
        this.password = password;
    }

    public abstract void login(String username, String password);

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return username;
    }

    public void setName(String name) {
        this.username = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    // Method to save Employee object to a file
    public void saveToFile(String fileName) {
        try (PrintWriter writer = new PrintWriter(new File(fileName))) {
            writer.println(username);
            writer.println(grade);
            writer.println(password);
            System.out.println("Employee saved to file: " + fileName);
        } catch (FileNotFoundException e) {
            System.out.println("Error saving employee to file: " + e.getMessage());
        }
    }

    // Static method to load Employee object from a file
    public static Employee loadFromFile(String fileName) {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            String username = scanner.nextLine();
            String grade = scanner.nextLine();
            String password = scanner.nextLine();
            System.out.println("Employee loaded from file: " + fileName);
            return new Employee(username, grade, password) {
                @Override
                public void login(String username, String password) {
                    // Implementation of the login method can be added here
                }
            };
        } catch (FileNotFoundException e) {
            System.out.println("Error loading employee from file: " + e.getMessage());
        }
        return null;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "username='" + username + '\'' +
                ", grade='" + grade + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
