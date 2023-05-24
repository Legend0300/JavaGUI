package com.example;

public abstract class HOD extends Employee{
    Employee hodDetails;

    public HOD(String username, String grade, String password, Employee hodDetails) {
        super(username, grade, password);
        this.hodDetails = hodDetails;
    }

    public Employee getHodDetails() {
        return hodDetails;
    }

    public void setHodDetails(Employee hodDetails) {
        this.hodDetails = hodDetails;
    }

    public void login(String username, String password) {

    }

    @Override
    public String toString() {
        return "HOD{" +
                "hodDetails=" + hodDetails +
                '}';
    }
}
