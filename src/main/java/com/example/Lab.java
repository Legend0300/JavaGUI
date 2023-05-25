package com.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Lab implements Serializable {
    private LabStaff incharge;
    private String labName;
    private boolean hasProjector;
    private List<Pc> pcs;

    public Lab(LabStaff incharge, String labName, boolean hasProjector) {
        this.incharge = incharge;
        this.labName = labName;
        this.hasProjector = hasProjector;
        this.pcs = new ArrayList<>();
    }

    public LabStaff getIncharge() {
        return incharge;
    }

    public void setIncharge(LabStaff incharge) {
        this.incharge = incharge;
    }

    public String getLabName() {
        return labName;
    }

    public void setLabName(String labName) {
        this.labName = labName;
    }

    public boolean isHasProjector() {
        return hasProjector;
    }

    public void setHasProjector(boolean hasProjector) {
        this.hasProjector = hasProjector;
    }

    public List<Pc> getPcs() {
        return pcs;
    }

    public void setPcs(List<Pc> pcs) {
        this.pcs = pcs;
    }

    public void addPc(Pc pc) {
        pcs.add(pc);
    }

    public void removePc(Pc pc) {
        pcs.remove(pc);
    }

    public Pc getPc(int pcID) {
        for (Pc pc : pcs) {
            if (pc.getId() == pcID) {
                return pc;
            }
        }
        return null;
    }

    // Method to save Lab object to a file
    public void saveToFile(String fileName) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(this);
            System.out.println("Lab saved to file: " + fileName);
        } catch (IOException e) {
            System.out.println("Error saving Lab to file: " + e.getMessage());
        }
    }

    // Static method to load Lab object from a file
    public static Lab loadFromFile(String fileName) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            Lab lab = (Lab) inputStream.readObject();
            System.out.println("Lab loaded from file: " + fileName);
            return lab;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading Lab from file: " + e.getMessage());
        }
        return null;
    }

    @Override
    public String toString() {
        return "Lab{" +
                "incharge=" + incharge +
                ", labName='" + labName + '\'' +
                ", hasProjector=" + hasProjector +
                ", pcs=" + pcs +
                '}';
    }
}
