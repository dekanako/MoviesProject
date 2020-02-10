package com.softwaresupermacy.androidtest.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.softwaresupermacy.androidtest.database.entity.Movie;

import java.util.List;

@Dao
public interface MoviesDao {
    @Insert
    void insertMovies(List<Movie> movie);

    @Query("SELECT * FROM MOVIE LIMIT 1")
    Movie hasMovie();

    @Query("SELECT DISTINCT Movie.mPackage FROM Movie")
    List<String>getPackages();

    @Query("SELECT * FROM Movie WHERE Movie.mPackage = :passedPackage")
    List<Movie> getMoviesByPackages(String passedPackage);
    @Query("DELETE FROM Movie")
    void deleteAll();
}
