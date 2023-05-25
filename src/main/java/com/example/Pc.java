package com.example;

import java.io.*;

public class Pc implements Serializable {
    private int id;
    private String name;
    private String speed;
    private String RAM;
    private String diskSize;
    private String screen;

    public Pc(int id, String name, String speed, String RAM, String diskSize, String screen) {
        this.id = id;
        this.name = name;
        this.speed = speed;
        this.RAM = RAM;
        this.diskSize = diskSize;
        this.screen = screen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getRAM() {
        return RAM;
    }

    public void setRAM(String RAM) {
        this.RAM = RAM;
    }

    public String getDiskSize() {
        return diskSize;
    }

    public void setDiskSize(String diskSize) {
        this.diskSize = diskSize;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    @Override
    public String toString() {
        return "Pc{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", speed='" + speed + '\'' +
                ", RAM='" + RAM + '\'' +
                ", diskSize='" + diskSize + '\'' +
                ", screen='" + screen + '\'' +
                '}';
    }

    // Method to save Pc object to a file
    public void saveToFile(String fileName) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(this);
            System.out.println("Pc saved to file: " + fileName);
        } catch (IOException e) {
            System.out.println("Error saving Pc to file: " + e.getMessage());
        }
    }

    // Static method to load Pc object from a file
    public static Pc loadFromFile(String fileName) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            Pc pc = (Pc) inputStream.readObject();
            System.out.println("Pc loaded from file: " + fileName);
            return pc;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading Pc from file: " + e.getMessage());
        }
        return null;
    }
}
