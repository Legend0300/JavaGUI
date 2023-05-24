package com.example;
import java.util.ArrayList;

public class Department {
    HOD hod;
    String departmentName;
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


}
