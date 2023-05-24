
package com.example;
public class LabStaff extends Employee{
    Employee staffDetails;

    public LabStaff(String username, String grade, String password, Employee staffDetails) {
        super(username, grade, password);
        this.staffDetails = staffDetails;
    }
    public void login(String username, String password) {

    }

    public Employee getStaffDetails() {
        return staffDetails;
    }

    public void setStaffDetails(Employee staffDetails) {
        this.staffDetails = staffDetails;
    }

    @Override
    public String toString() {
        return "LabStaff{" +
                "staffDetails=" + staffDetails +
                '}';
    }
}
