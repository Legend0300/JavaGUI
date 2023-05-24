package com.example;

import java.util.ArrayList;

public class Campus {
    String campusName;
    String address;
    Director director;
    ArrayList<Department> departments =new ArrayList<>();

    public Campus(String campusName, String address, Director director) {
        this.campusName = campusName;
        this.address = address;
        this.director = director;
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

}
