package com.mrbhati.vizitors.Model;

import java.util.Date;

public class VisitSearchResponse {
    public String id;
    public String name;
    public String mobile;
    public Object email;
    public String society;
    public String picture;
    public Object deleted_at;
    public Date created_at;
    public Date updated_at;


    public VisitSearchResponse(String id, String name, String mobile, Object email, String society, String picture, Object deleted_at, Date created_at, Date updated_at) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.society = society;
        this.picture = picture;
        this.deleted_at = deleted_at;
        this.created_at = created_at;
        this.updated_at = updated_at;
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

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
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

    public Object getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(Object deleted_at) {
        this.deleted_at = deleted_at;
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
}


