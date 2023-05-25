package com.example;

import java.io.*;
import java.util.Scanner;

public class HOD extends Employee implements Serializable {
    private Employee hodDetails;

    public HOD(String username, String grade, String password) {
        super(username, grade, password);
    }

    public Employee getHodDetails() {
        return hodDetails;
    }

    public void setHodDetails(Employee hodDetails) {
        this.hodDetails = hodDetails;
    }

    // Method to save HOD object to a file
    public void saveToFile(String fileName) {
        try (PrintWriter writer = new PrintWriter(new File(fileName))) {
            writer.println(getUsername());
            writer.println(getGrade());
            writer.println(getPassword());
            writer.println(hodDetails.getUsername());
            writer.println(hodDetails.getGrade());
            writer.println(hodDetails.getPassword());
            System.out.println("HOD saved to file: " + fileName);
        } catch (FileNotFoundException e) {
            System.out.println("Error saving HOD to file: " + e.getMessage());
        }
    }

    // Static method to load HOD object from a file
    public static HOD loadFromFile(String fileName) {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            String username = scanner.nextLine();
            String grade = scanner.nextLine();
            String password = scanner.nextLine();
            String hodUsername = scanner.nextLine();
            String hodGrade = scanner.nextLine();
            String hodPassword = scanner.nextLine();
            Employee hodDetails = new Employee(hodUsername, hodGrade, hodPassword) {
                @Override
                public void login(String username, String password) {
                    // Implementation of the login method can be added here
                }
            };
            HOD hod = new HOD(username, grade, password) {
                @Override
                public void login(String username, String password) {
                    // Implementation of the login method can be added here
                }
            };
            System.out.println("HOD loaded from file: " + fileName);
            return hod;
        } catch (FileNotFoundException e) {
            System.out.println("Error loading HOD from file: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void login(String username, String password) {
        // Implementation of the login method can be added here
    }

    @Override
    public String toString() {
        return "HOD{" +
                "hodDetails=" + hodDetails +
                '}';
    }
}
