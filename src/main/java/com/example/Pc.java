package com.example;
public class Pc {
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
}
