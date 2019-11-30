package com.manoj.niramai_assignment.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DataModel implements Serializable {
    private String projectname,shortdesc,longdesc,companyname,creationdate;

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getShortdesc() {
        return shortdesc;
    }

    public void setShortdesc(String shortdesc) {
        this.shortdesc = shortdesc;
    }

    public String getLongdesc() {
        return longdesc;
    }

    public void setLongdesc(String longdesc) {
        this.longdesc = longdesc;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getCreationdate() {
        return creationdate;
    }

    public void setCreationdate(String creationdate) {
        this.creationdate = creationdate;
    }

    public DataModel(String projectname, String shortdesc, String longdesc, String companyname, String creationdate) {
        this.projectname = projectname;
        this.shortdesc = shortdesc;
        this.longdesc = longdesc;
        this.companyname = companyname;
        this.creationdate = creationdate;
    }

    public static List<DataModel> createMovies(int itemCount) {
        List<DataModel> movies = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            DataModel movie = new DataModel("project " + (itemCount == 0 ?
                    (itemCount + 1 + i) : (itemCount + i)),
                    "brief desc " + (itemCount == 0 ?
                            (itemCount + 1 + i) : (itemCount + i)),
                    "long desc " + (itemCount == 0 ?
                            (itemCount + 1 + i) : (itemCount + i)),
                    "company name  " + (itemCount == 0 ?
                            (itemCount + 1 + i) : (itemCount + i)),
                    "2019 November - " + (itemCount == 0 ?
                            (itemCount + 1 + i) : (itemCount + i)));
            movies.add(movie);
        }
        return movies;
    }
}


