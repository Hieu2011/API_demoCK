package com.example.win7x64.thuynhan;

import java.io.Serializable;

public class Date implements Serializable {
    private int id;
    private String projectname;
    private String datestart;
    private String datefinish;

    public Date(int id, String projectname, String datestart, String datefinish) {
        this.id = id;
        this.projectname = projectname;
        this.datestart = datestart;
        this.datefinish = datefinish;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getDatestart() {
        return datestart;
    }

    public void setDatestart(String datestart) {
        this.datestart = datestart;
    }

    public String getDatefinish() {
        return datefinish;
    }

    public void setDatefinish(String datefinish) {
        this.datefinish = datefinish;
    }
}
