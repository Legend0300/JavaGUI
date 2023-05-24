package com.example;

public class Director extends Employee {
    Employee directorDetails;

    public Director(String username, String grade, String password, Employee directorDetails) {
        super(username, grade, password);
        this.directorDetails = directorDetails;
    }

    public Employee getDirectorDetails() {
        return directorDetails;
    }

    public void setDirectorDetails(Employee directorDetails) {
        this.directorDetails = directorDetails;
    }
    public void login(String username,String password){

    }

    @Override
    public String toString() {
        return "Director{" +
                "directorDetails=" + directorDetails +
                '}';
    }
}
