package com.softwaresupermacy.androidtest.database.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GenreContainer {
    @SerializedName("genres")
    private List<Genre> mGenres;

    public GenreContainer(List<Genre> mGenres) {
        this.mGenres = mGenres;
    }

    public List<Genre> getGenres() {
        return mGenres;
    }

    public void setmGenres(List<Genre> mGenres) {
        this.mGenres = mGenres;
    }
}
