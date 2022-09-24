package com.mrbhati.vizitors.Model;


import java.util.ArrayList;

public class VisitorModel{
    public int current_page;
    public ArrayList<Datum> data;
    public String first_page_url;
    public int from;
    public int last_page;
    public String last_page_url;
    public ArrayList<Link> links;
    public String next_page_url;
    public String path;
    public int per_page;
    public String prev_page_url;
    public int myto;
    public int total;

    public VisitorModel(int current_page, ArrayList<Datum> data, String first_page_url, int from, int last_page, String last_page_url, ArrayList<Link> links, String next_page_url, String path, int per_page, String prev_page_url, int myto, int total) {
        this.current_page = current_page;
        this.data = data;
        this.first_page_url = first_page_url;
        this.from = from;
        this.last_page = last_page;
        this.last_page_url = last_page_url;
        this.links = links;
        this.next_page_url = next_page_url;
        this.path = path;
        this.per_page = per_page;
        this.prev_page_url = prev_page_url;
        this.myto = myto;
        this.total = total;
    }

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public ArrayList<Datum> getData() {
        return data;
    }

    public void setData(ArrayList<Datum> data) {
        this.data = data;
    }

    public String getFirst_page_url() {
        return first_page_url;
    }

    public void setFirst_page_url(String first_page_url) {
        this.first_page_url = first_page_url;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getLast_page() {
        return last_page;
    }

    public void setLast_page(int last_page) {
        this.last_page = last_page;
    }

    public String getLast_page_url() {
        return last_page_url;
    }

    public void setLast_page_url(String last_page_url) {
        this.last_page_url = last_page_url;
    }

    public ArrayList<Link> getLinks() {
        return links;
    }

    public void setLinks(ArrayList<Link> links) {
        this.links = links;
    }

    public String getNext_page_url() {
        return next_page_url;
    }

    public void setNext_page_url(String next_page_url) {
        this.next_page_url = next_page_url;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public String getPrev_page_url() {
        return prev_page_url;
    }

    public void setPrev_page_url(String prev_page_url) {
        this.prev_page_url = prev_page_url;
    }

    public int getMyto() {
        return myto;
    }

    public void setMyto(int myto) {
        this.myto = myto;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}