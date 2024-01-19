package com.privatemoviecollection.be;

import java.util.Date;
import java.util.List;

public class Movie {
    private int id;
    private String name;
    private int rating;
    private String fileLink;
    private Date date;

    public Movie(){

    }

    public Movie(String name, int rating, String fileLink, Date date) {
        this.name = name;
        this.rating = rating;
        this.fileLink = fileLink;
        this.date = date;
    }

    public Movie(int id, String name, int rating, String fileLink, Date date) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.fileLink = fileLink;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getFileLink() {
        return fileLink;
    }

    public void setFileLink(String fileLink) {
        this.fileLink = fileLink;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

