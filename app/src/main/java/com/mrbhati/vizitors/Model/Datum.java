package com.mrbhati.vizitors.Model;

import java.util.Date;

public class Datum {
   private String id;
   private String picture;
   private String name;
   private String mobile;
   private String society;
   private int total;
   private Date createdAt;
    private Date last_visit_at;
    public Date getLastVisitAt() {
        return last_visit_at;
    }

    public void setLastVisitAt(Date last_visit_at) {
        this.last_visit_at = last_visit_at;
    }




   public Datum(String id,String picture,String name,String mobile,String society,int total,Date createdAt, Date last_visit_at){
       this.id = id;
       this.name = name;
       this.picture = picture;
       this.mobile = mobile;
       this.society = society;
       this.total = total;
       this.createdAt = createdAt;
       this.last_visit_at = last_visit_at;

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
   public int getTotal() {
       return total;
   }
   public void setTotal(Integer total) {
       this.total = total;
   }
   public Date getCreatedAt() {
       return createdAt;
   }
   public void setCreatedAt(Date createdAt) {
       this.createdAt = createdAt;
   }

}
