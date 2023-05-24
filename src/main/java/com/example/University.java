package com.example;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class University {
    String name;
    ArrayList<Campus> campuses =new ArrayList<>();

    public University(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Campus> getCampuses() {
        return campuses;
    }

    public void setCampuses(ArrayList<Campus> campuses) {
        this.campuses = campuses;
    }
    public void addCampus(Campus c){
        campuses.add(c);
    }
    public void removeCampus(Campus c){
        campuses.remove(c);
    }
    public Campus getCampus(String campusName){
        for(Campus c:campuses){
            if(c.getCampusName().equals(campusName)){
                return c;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "University{" +
                "name='" + name + '\'' +
                ", campuses=" + campuses +
                '}';
    }

}
