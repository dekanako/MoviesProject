package com.softwaresupermacy.androidtest.database.entity;

import java.util.List;

public class PackagedMovie {
    private List<Movie> mMovies;
    private String mPackage;

    public PackagedMovie(List<Movie> movies, String passedPackage) {
        mMovies = movies;
        mPackage = passedPackage;
    }

    public List<Movie> getMovies() {
        return mMovies;
    }

    public void setMovies(List<Movie> movies) {
        mMovies = movies;
    }

    public String getPackage() {
        return mPackage;
    }

    public void setPackage(String aPackage) {
        mPackage = aPackage;
    }
}
