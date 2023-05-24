package com.example;

abstract public class Employee {
   private String username;
    private String grade;
    private String password;
    public abstract void password(String password);
    public abstract void username(String username);

    public Employee(String username, String grade, String password) {
        this.username = username;
        this.grade = grade;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return username;
    }

    public void setName(String name) {
        this.username = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }




    @Override
    public String toString() {
        return "Employee{" +
                "username='" + username + '\'' +
                ", grade='" + grade + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
