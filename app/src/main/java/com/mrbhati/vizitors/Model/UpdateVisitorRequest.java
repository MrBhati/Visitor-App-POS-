package com.mrbhati.vizitors.Model;

public class UpdateVisitorRequest {

    String name;
    String mobile;
    String society;
    String photo;

    public UpdateVisitorRequest(String name, String mobile, String society, String photo) {
        this.name = name;
        this.mobile = mobile;
        this.society = society;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
