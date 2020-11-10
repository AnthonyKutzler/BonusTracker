package com.company;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Person {

    private String id;
    private String fName;
    private String lName;
    private LocalDate date;
    private Map<String, Double> hours2;
    private double hours;
    private double hoursCount = 0;
    private double pto;

    public Person(String id){
        this.id = id;
        hours = 0;
        pto = 0;
        hours2 = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        String[] names = name.split(",");
        fName = names[1].trim();
        lName = names[0].trim();
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getHours() {
        return hours;
    }

    public void addHours(double hours) {
        //if(hours>0)
            hoursCount += .5;
        this.hours += hours;
    }

    public void addHours2(double hours, boolean add) {
        if(add) {
            hoursCount += .5;
            this.hours += hours;
        }
    }

    public double getPto() {
        return pto;
    }

    public void addPto(double pto){
        this.pto += pto;
    }

    public void addHours2(String date, double hours){
        hours2.put(date, hours);
    }

    public double getHoursCount() {
        return hoursCount;
    }
}
