package com.example;
import java.util.ArrayList;
import java.io.*;

public class Department {
    HOD hod;
    private String departmentName;
    ArrayList<Lab> labs = new ArrayList<>();

    public Department(HOD hod, String departmentName) {
        this.hod = hod;
        this.departmentName = departmentName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public HOD getHod() {
        return hod;
    }

    public void setHod(HOD hod) {
        this.hod = hod;
    }

    public ArrayList<Lab> getLabs() {
        return labs;
    }

    public void setLabs(ArrayList<Lab> labs) {
        this.labs = labs;
    }

    @Override
    public String toString() {
        return "Department{" +
                "hod=" + hod +
                ", labs=" + labs +
                '}';
    }

    public void addLab(Lab l){
        labs.add(l);
    }
    public void removeLab(Lab l){
        labs.remove(l);
    }
    public Lab getLab(String labName){
        for(Lab l: labs){
            if(l.getLabName().equals(labName))
                return l;
        }
        return null;
    }
    public void saveToFile(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(this);
            System.out.println("Department saved to file: " + fileName);
        } catch (IOException e) {
            System.out.println("Error saving department to file: " + e.getMessage());
        }
    }

    // Static method to load Department object from a file
    public static Department loadFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            Department department = (Department) ois.readObject();
            System.out.println("Department loaded from file: " + fileName);
            return department;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading department from file: " + e.getMessage());
        }
        return null;
    }


}
