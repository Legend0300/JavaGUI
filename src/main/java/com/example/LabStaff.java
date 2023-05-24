
package com.example;
public class LabStaff extends Employee{
    Employee staffDetails;

    public LabStaff(String name, String grade) {
        super(name, grade);
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
