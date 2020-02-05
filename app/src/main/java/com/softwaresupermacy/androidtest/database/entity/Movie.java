package com.softwaresupermacy.androidtest.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import com.google.gson.annotations.SerializedName;

@Entity
public class Movie
{
    @SerializedName("poster_path")
    private String imageLink;

    @SerializedName("title")
    private String filmTitle;

    @SerializedName("id")
    private int dbMovieId;

    @SerializedName("overview")
    private String overView;

    private String mPackage;

    public Movie(String imageLink, String filmTitle,int dbMovieId, String overView, String mPackage)
    {
        this.imageLink = imageLink;
        this.filmTitle = filmTitle;
        this.dbMovieId = dbMovieId;
        this.overView = overView;
        this.mPackage = mPackage;
    }


    public String getOverView() {
        return overView;
    }

    public void setOverView(String overView) {
        this.overView = overView;
    }

    public int getDbMovieId() {
        return dbMovieId;
    }

    public void setDbMovieId(int dbMovieId) {
        this.dbMovieId = dbMovieId;
    }

    public String getImageLink()
    {
        return imageLink;
    }

    public void setImageLink(String imageLink)
    {
        this.imageLink = imageLink;
    }

    public String getFilmTitle()
    {
        return filmTitle;
    }

    public void setFilmTitle(String filmTitle)
    {
        this.filmTitle = filmTitle;
    }

    public String getmPackage() {
        return mPackage;
    }

    public void setmPackage(String mPackage) {
        this.mPackage = mPackage;
    }

    @NonNull
    @Override
    public String toString() {
        return this.filmTitle + " -- "+this.mPackage;
    }
}
