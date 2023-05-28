package com.example;

import java.io.*;
import java.util.ArrayList;

class University implements Serializable {
    private String name;
    private ArrayList<Campus> campuses = new ArrayList<>();

    public University(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Campus> getCampuses() {
        return campuses;
    }

    public void setCampuses(ArrayList<Campus> campuses) {
        this.campuses = campuses;
    }

    public void addCampus(Campus c) {
        campuses.add(c);
    }

    public void removeCampus(Campus c) {
        campuses.remove(c);
    }

    public Campus getCampus(String campusName) {
        for (Campus c : campuses) {
            if (c.getCampusName().equals(campusName)) {
                return c;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "University{" +
                "name='" + name + '\'' +
                ", campuses=" + campuses +
                '}';
    }

    // Method to save University object to a file
    public void saveToFile(String fileName) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(this);
            System.out.println("University saved to file: " + fileName);
        } catch (IOException e) {
            System.out.println("Error saving University to file: " + e.getMessage());
        }
    }

    // Method to load University object from a file
    public static University loadFromFile(String fileName) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            University university = (University) inputStream.readObject();
            System.out.println("University loaded from file: " + fileName);
            return university;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading University from file: " + e.getMessage());
        }
        return null;
    }

    // Method to save ArrayList of University objects to a file
    public static void saveAll(ArrayList<University> universities, String fileName) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(universities);
            System.out.println("Universities saved to file: " + fileName);
        } catch (IOException e) {
            System.out.println("Error saving Universities to file: " + e.getMessage());
        }
    }

    // Method to load ArrayList of University objects from a file
    public static ArrayList<University> loadAll(String fileName) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            ArrayList<University> universities = (ArrayList<University>) inputStream.readObject();
            System.out.println("Universities loaded from file: " + fileName);
            return universities;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading Universities from file: " + e.getMessage());
        }
        return new ArrayList<>();
    }
}
