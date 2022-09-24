package com.mrbhati.vizitors.Model;

public class Department {
    public String id;
    public String name;
    public String designation;
    public Object location;

    public Department(String id, String name, String designation, Object location) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Object getLocation() {
        return location;
    }

    public void setLocation(Object location) {
        this.location = location;
    }
}