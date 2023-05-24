package com.example;

public class Director extends Employee {
    Employee directorDetails;

    public Director(String name, String grade) {
        super(name, grade);
    }

    public Employee getDirectorDetails() {
        return directorDetails;
    }

    public void setDirectorDetails(Employee directorDetails) {
        this.directorDetails = directorDetails;
    }

    @Override
    public String toString() {
        return "Director{" +
                "directorDetails=" + directorDetails +
                '}';
    }
}
