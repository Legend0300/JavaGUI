package com.example;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class Campus {
    private String campusName;
    private String address;
    Director director;
    ArrayList<Department> departments =new ArrayList<>();

    public Campus(String campusName, String address, Director director) {
        this.campusName = campusName;
        this.address = address;
        this.director = director;
    }

    public Campus(String campusName, String address) {
        this.campusName = campusName;
        this.address = address;
    }

    public Campus(String campusName) {
        this.campusName = campusName;
    }

    @Override
    public String toString() {
        return "Campus{" +
                "campusName='" + campusName + '\'' +
                ", address='" + address + '\'' +
                ", director=" + director +
                ", departments=" + departments +
                '}';
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public ArrayList<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(ArrayList<Department> departments) {
        this.departments = departments;
    }

    public String getCampusName() {
        return campusName;
    }

    public void setCampusName(String campusName) {
        this.campusName = campusName;
    }
    public void addDepartment(Department d){
        departments.add(d);
    }
    public void addLab(Department d){
        departments.add(d);
    }
    public void removeLab(Department d){
        departments.remove(d);
    }
    public Department getLab(String departmentName){    
        for(Department d: departments){
            if(d.getDepartmentName().equals(departmentName))
                return d;
        }
        return null;
    }
    public void saveToFile(String fileName) {
        try (PrintWriter writer = new PrintWriter(new File(fileName))) {
            writer.println(campusName);
            writer.println(address);
            writer.println(director.getName());
            for (Department department : departments) {
                writer.println(department.getDepartmentName());
            }
            System.out.println("Campus saved to file: " + fileName);
        } catch (FileNotFoundException e) {
            System.out.println("Error saving campus to file: " + e.getMessage());
        }
    }

    // Static method to load Campus object from a file
    public static Campus loadFromFile(String fileName) {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            String campusName = scanner.nextLine();
            String address = scanner.nextLine();
            String directorName = scanner.nextLine();
            Director director = new Director(directorName, directorName, directorName);

            Campus campus = new Campus(campusName, address, director);
            while (scanner.hasNextLine()) {
                String departmentName = scanner.nextLine();
                campus.addDepartment(new Department(null, departmentName));
            }
            System.out.println("Campus loaded from file: " + fileName);
            return campus;
        } catch (FileNotFoundException e) {
            System.out.println("Error loading campus from file: " + e.getMessage());
        }
        return null;
    }

}
