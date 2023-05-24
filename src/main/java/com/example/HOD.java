public class HOD extends Employee{
    Employee hodDetails;

    public HOD(String name, String grade) {
        super(name, grade);
    }

    public Employee getHodDetails() {
        return hodDetails;
    }

    public void setHodDetails(Employee hodDetails) {
        this.hodDetails = hodDetails;
    }

    @Override
    public String toString() {
        return "HOD{" +
                "hodDetails=" + hodDetails +
                '}';
    }
}
