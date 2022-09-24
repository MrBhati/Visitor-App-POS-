package com.mrbhati.vizitors.Model;

public class Visitor{
    public String id;
    public String name;
    public String mobile;
    public String email;
    public String society;
    public String picture;

    public Visitor(String id, String name, String mobile, String email, String society, String picture) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.society = society;
        this.picture = picture;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSociety() {
        return society;
    }

    public void setSociety(String society) {
        this.society = society;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}