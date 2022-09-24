package com.mrbhati.vizitors.Model;

import java.util.Date;

public class Visit {

    public String id;
    public String picture;
    public String code;
    public String purpose;
    public String visitor_id;
    public String visitor;
    public String department;
    public String designation;
    public String mobile;
    public String society;
    public Date created_at;

    public Visit(String id, String picture, String code, String purpose, String visitor_id, String visitor, String department, String designation, String mobile, String society, Date created_at) {
        this.id = id;
        this.picture = picture;
        this.code = code;
        this.purpose = purpose;
        this.visitor_id = visitor_id;
        this.visitor = visitor;
        this.department = department;
        this.designation = designation;
        this.mobile = mobile;
        this.society = society;
        this.created_at = created_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getVisitor_id() {
        return visitor_id;
    }

    public void setVisitor_id(String visitor_id) {
        this.visitor_id = visitor_id;
    }

    public String getVisitor() {
        return visitor;
    }

    public void setVisitor(String visitor) {
        this.visitor = visitor;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSociety() {
        return society;
    }

    public void setSociety(String society) {
        this.society = society;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
}
