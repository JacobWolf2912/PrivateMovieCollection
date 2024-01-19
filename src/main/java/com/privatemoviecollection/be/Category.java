package com.privatemoviecollection.be;

import java.util.ArrayList;

public class Category {
    private int id;
    private String name;
    private ArrayList<Movie> movies;

    public Category(){

    }

    public Category(String name) {
        this.name = name;
        this.movies = new ArrayList<Movie>();
    }

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
        this.movies = new ArrayList<Movie>();
    }


    public Category(int id, String name, ArrayList<Movie> movies) {
        this.id = id;
        this.name = name;
        this.movies = movies;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
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
}

