package com.example;

import java.io.*;

public class LabStaff extends Employee implements Serializable {
    private Employee staffDetails;

    public LabStaff(String username, String grade, String password, Employee staffDetails) {
        super(username, grade, password);
        this.staffDetails = staffDetails;
    }

    public Employee getStaffDetails() {
        return staffDetails;
    }

    public void setStaffDetails(Employee staffDetails) {
        this.staffDetails = staffDetails;
    }

    public void login(String username, String password) {
        // Implement login functionality
    }

    // Method to save LabStaff object to a file
    public void saveToFile(String fileName) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(this);
            System.out.println("LabStaff saved to file: " + fileName);
        } catch (IOException e) {
            System.out.println("Error saving LabStaff to file: " + e.getMessage());
        }
    }

    // Static method to load LabStaff object from a file
    public static LabStaff loadFromFile(String fileName) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            LabStaff labStaff = (LabStaff) inputStream.readObject();
            System.out.println("LabStaff loaded from file: " + fileName);
            return labStaff;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading LabStaff from file: " + e.getMessage());
        }
        return null;
    }

    @Override
    public String toString() {
        return "LabStaff{" +
                "staffDetails=" + staffDetails +
                '}';
    }
}
