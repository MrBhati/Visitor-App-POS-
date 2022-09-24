package com.mrbhati.vizitors.Model;

import java.util.Date;




public class AddVisitResponse{
    public String code;
    public String visitor_id;
    public String department_id;
    public String user_id;
    public String purpose;
    public Date updated_at;
    public Date created_at;
    public String id;
    public Visitor visitor;
    public Department department;

    public AddVisitResponse(String code, String visitor_id, String department_id, String user_id, String purpose, Date updated_at, Date created_at, String id, Visitor visitor, Department department) {
        this.code = code;
        this.visitor_id = visitor_id;
        this.department_id = department_id;
        this.user_id = user_id;
        this.purpose = purpose;
        this.updated_at = updated_at;
        this.created_at = created_at;
        this.id = id;
        this.visitor = visitor;
        this.department = department;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getVisitor_id() {
        return visitor_id;
    }

    public void setVisitor_id(String visitor_id) {
        this.visitor_id = visitor_id;
    }

    public String getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(String department_id) {
        this.department_id = department_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Visitor getVisitor() {
        return visitor;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}


