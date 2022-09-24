package com.mrbhati.vizitors.Model;

public class AddVisitModel {
    String name;
    String mobile;
    String society;
    String department_id;
    String purpose;
    String photo;

    public AddVisitModel(String name, String mobile, String society, String department_id, String purpose, String photo) {
        this.name = name;
        this.mobile = mobile;
        this.society = society;
        this.department_id = department_id;
        this.purpose = purpose;
        this.photo = photo;
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

    public String getSociety() {
        return society;
    }

    public void setSociety(String society) {
        this.society = society;
    }

    public String getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(String department_id) {
        this.department_id = department_id;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
