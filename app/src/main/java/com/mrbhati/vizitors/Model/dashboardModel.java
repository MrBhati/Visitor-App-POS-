package com.mrbhati.vizitors.Model;

public class dashboardModel {
    public int todaysVisit;
    public int weeksVisit;
    public int totalVisit;
    public int totalVisitor;

    public dashboardModel(int todaysVisit, int weeksVisit, int totalVisit, int totalVisitor) {
        this.todaysVisit = todaysVisit;
        this.weeksVisit = weeksVisit;
        this.totalVisit = totalVisit;
        this.totalVisitor = totalVisitor;
    }

    public int getTodaysVisit() {
        return todaysVisit;
    }

    public void setTodaysVisit(int todaysVisit) {
        this.todaysVisit = todaysVisit;
    }

    public int getWeeksVisit() {
        return weeksVisit;
    }

    public void setWeeksVisit(int weeksVisit) {
        this.weeksVisit = weeksVisit;
    }

    public int getTotalVisit() {
        return totalVisit;
    }

    public void setTotalVisit(int totalVisit) {
        this.totalVisit = totalVisit;
    }

    public int getTotalVisitor() {
        return totalVisitor;
    }

    public void setTotalVisitor(int totalVisitor) {
        this.totalVisitor = totalVisitor;
    }
}


