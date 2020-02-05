package com.softwaresupermacy.androidtest.database;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.softwaresupermacy.androidtest.database.converters.Converter;
import com.softwaresupermacy.androidtest.database.dao.MoviesDao;
import com.softwaresupermacy.androidtest.database.entity.Movie;

@Database(entities = {Movie.class}, exportSchema = false, version = 1)
@TypeConverters({Converter.class})
public abstract class MovieDatabase extends RoomDatabase {
    private static MovieDatabase sInstance;
    public abstract MoviesDao dao();

    public synchronized static MovieDatabase getInstance(Application application){
        if (sInstance == null){
            sInstance = Room.databaseBuilder(application, MovieDatabase.class, "movie")
                    .build();
        }
        return sInstance;
    }

}
