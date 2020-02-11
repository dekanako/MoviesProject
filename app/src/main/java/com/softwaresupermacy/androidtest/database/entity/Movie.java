package com.softwaresupermacy.androidtest.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class Movie
{
    @PrimaryKey(autoGenerate = true)
    private int dbGenId;

    @SerializedName("poster_path")
    private String imageLink;

    @SerializedName("title")
    @ColumnInfo(name = "ganres")
    private String filmTitle;

    @SerializedName("id")
    private int dbMovieId;

    @SerializedName("overview")
    private String overView;

    @SerializedName("genre_ids")
    private GenresString mGenresString;

    private String mPackage;

    public Movie(String imageLink, String filmTitle,int dbMovieId, String overView, String mPackage, GenresString mGenresString)
    {
        this.imageLink = imageLink;
        this.filmTitle = filmTitle;
        this.dbMovieId = dbMovieId;
        this.overView = overView;
        this.mPackage = mPackage;
        this.mGenresString = mGenresString;
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

    public String getPackage() {
        return mPackage;
    }

    public void setPackage(String mPackage) {
        this.mPackage = mPackage;
    }

    public int getDbGenId() {
        return dbGenId;
    }

    public void setDbGenId(int dbGenId) {
        this.dbGenId = dbGenId;
    }

    public String getmPackage() {
        return mPackage;
    }

    public void setmPackage(String mPackage) {
        this.mPackage = mPackage;
    }

    public GenresString getGenresString() {
        return mGenresString;
    }

    public void setGenresString(GenresString genresString) {
        mGenresString = genresString;
    }

    @NonNull
    @Override
    public String toString() {
        return this.filmTitle + " -- "+this.mPackage;
    }
}
