
package com.example;
public class LabStaff extends Employee{
    Employee staffDetails;

    public LabStaff(String username, String grade, String password, Employee staffDetails) {
        super(username, grade, password);
        this.staffDetails = staffDetails;
    }

    public Employee getStaffDetails() {
        return staffDetails;
    }

    public void setStaffDetails(Employee staffDetails) {
        this.staffDetails = staffDetails;
    }
    @Override
    public void login(String username, String password) {
        {


            if (username.equals(getUsername()) && password.equals(getPassword()))
            {
                System.out.println("Login successful!");
                // Perform further actions or return a success flag
            }
            else
            {
                System.out.println("Invalid username or password!");
                // Perform further actions or return a failure flag
            }
        }

    }

    @Override
    public String toString() {
        return "LabStaff{" +
                "staffDetails=" + staffDetails +
                '}';
    }
};


