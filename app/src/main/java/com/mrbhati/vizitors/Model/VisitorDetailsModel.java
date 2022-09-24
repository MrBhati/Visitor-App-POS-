package com.mrbhati.vizitors.Model;

import java.util.Date;

public class VisitorDetailsModel {

    public String id;
    public String name;
    public String mobile;
    public String email;
    public String society;
    public String picture;
    public Date created_at;
    public Date updated_at;
    public Date last_visit_at;
    public int total;
    public Date getLast_visit_at() {
        return last_visit_at;
    }

    public void setLast_visit_at(Date last_visit_at) {
        this.last_visit_at = last_visit_at;
    }




    public VisitorDetailsModel(String id, String name, String mobile, String email, String society, String picture, Date created_at, Date updated_at, Date last_visit_at, int total) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.society = society;
        this.picture = picture;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.last_visit_at = last_visit_at;
        this.total = total;
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

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
