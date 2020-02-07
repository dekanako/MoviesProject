package com.softwaresupermacy.androidtest.database.converters;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.TypeConverter;

import com.softwaresupermacy.androidtest.GenreListProvider;
import com.softwaresupermacy.androidtest.database.MovieDatabase;
import com.softwaresupermacy.androidtest.database.entity.Genre;
import com.softwaresupermacy.androidtest.database.entity.Movie;
import com.softwaresupermacy.androidtest.database.entity.PackagedMovie;

import java.util.ArrayList;
import java.util.List;


public class Converter {
    @TypeConverter
    public static String fromNumbers(int [] numbers){
        StringBuilder genreBuilder = new StringBuilder();

        for (int count = 0; count<numbers.length; count++) {

            for (Genre gen: GenreListProvider.getGenres()) {

                if (numbers[count] == gen.getId()){
                        genreBuilder.append(gen.getName());
                }

            }
            if ((count < (numbers.length-1))){
                genreBuilder.append(", ");
            }
        }
        return genreBuilder.toString();
    }

    //TODO Fix this TypeConverter
    @TypeConverter
    public static int [] fromGenreString(String genre){
        return new int[]{34,23,32};
    }

}
