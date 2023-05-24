import java.util.ArrayList;

public class Lab {
    LabStaff incharge;
    String labName;
    boolean hasProjector;
    ArrayList<Pc> Pc = new ArrayList<>();


    @Override
    public String toString() {
        return "Lab{" +
                "incharge=" + incharge +
                ", labName='" + labName + '\'' +
                ", hasProjector=" + hasProjector +
                ", Pc=" + Pc +
                '}';
    }


    public Lab(LabStaff incharge, String labName, boolean hasProjector) {
        this.incharge = incharge;
        this.labName = labName;
        this.hasProjector = hasProjector;
    }

    public Lab(LabStaff incharge, String labName) {
        this.incharge = incharge;
        this.labName = labName;
    }

    public LabStaff getIncharge() {
        return incharge;
    }

    public void setIncharge(LabStaff incharge) {
        this.incharge = incharge;
    }

    public String getLabName() {
        return labName;
    }

    public void setLabName(String labName) {
        this.labName = labName;
    }

    public boolean isHasProjector() {
        return hasProjector;
    }

    public void setHasProjector(boolean hasProjector) {
        this.hasProjector = hasProjector;
    }

    public ArrayList<Pc> getPc() {
        return Pc;
    }

    public void setPc(ArrayList<Pc> pc) {
        Pc = pc;
    }

    public void addPC(Pc p){
        Pc.add(p);
    }
    public void removePc(Pc p){
        Pc.remove(p);
    }
    public Pc getPc(int pcID){
        for(Pc p:Pc){
            if(p.getId()==pcID){
                return p;
            }
        }
        return null;
    }

}
