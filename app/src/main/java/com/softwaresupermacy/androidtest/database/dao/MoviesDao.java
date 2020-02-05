package com.softwaresupermacy.androidtest.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.softwaresupermacy.androidtest.database.entity.Movie;

import java.util.List;

@Dao
public interface MoviesDao {

    @Query("SELECT * FROM Movie")
    LiveData<List<Movie>> getAllMovies();

    @Insert
    void insertMovies(Movie ...movie);

    @Insert
    void insertMovies(List<Movie> movie);
}
