package com.softwaresupermacy.androidtest.database.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MoviesList {

    @SerializedName("results")
    private List<Movie> mMovieList;
    private static String mMoviesPackage;

    public List<Movie> getmMovieList() {
        return mMovieList;
    }

    public void setmMovieList(List<Movie> mMovieList) {
        this.mMovieList = mMovieList;
    }

    public static String getmMoviesPackage() {
        return mMoviesPackage;
    }

    public static void setmMoviesPackage(String mMoviesPackage) {
        MoviesList.mMoviesPackage = mMoviesPackage;
    }

    public MoviesList(List<Movie> movieList) {
        mMovieList = movieList;
    }

    public List<Movie> getMovieList() {
        return mMovieList;
    }

    public void setMovieList(List<Movie> movieList) {
        mMovieList = movieList;
    }
}
