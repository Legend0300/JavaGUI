package com.example;
import java.io.*;
import java.util.Scanner;

public class Director extends Employee {
    Employee directorDetails;

    public Director(String username, String grade, String password) {
        super(username, grade, password);
    }

    public Employee getDirectorDetails() {
        return directorDetails;
    }

    public void setDirectorDetails(Employee directorDetails) {
        this.directorDetails = directorDetails;
    }
    public void login(String username,String password){

    }

    @Override
    public String toString() {
        return "Director{}";
    }

    public void saveToFile(String fileName) {
        try (PrintWriter writer = new PrintWriter(new File(fileName))) {
            writer.println(getUsername());
            writer.println(getGrade());
            writer.println(getPassword());
            writer.println(directorDetails.getUsername());
            writer.println(directorDetails.getGrade());
            writer.println(directorDetails.getPassword());
            System.out.println("Director saved to file: " + fileName);
        } catch (FileNotFoundException e) {
            System.out.println("Error saving director to file: " + e.getMessage());
        }
    }

    // Static method to load Director object from a file
    public static Director loadFromFile(String fileName) {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            String username = scanner.nextLine();
            String grade = scanner.nextLine();
            String password = scanner.nextLine();
            String directorUsername = scanner.nextLine();
            String directorGrade = scanner.nextLine();
            String directorPassword = scanner.nextLine();
            // Employee directorDetails = new Employee(directorUsername, directorGrade, directorPassword);
            // Director director = new Director(username, grade, password, directorDetails);
            // System.out.println("Director loaded from file: " + fileName);
            // return director;
        } catch (FileNotFoundException e) {
            System.out.println("Error loading director from file: " + e.getMessage());
        }
        return null;
    }
}
