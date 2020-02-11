package com.softwaresupermacy.androidtest.database.converters;

import androidx.room.TypeConverter;

import com.softwaresupermacy.androidtest.GenreListProvider;

import com.softwaresupermacy.androidtest.database.entity.Genre;
import com.softwaresupermacy.androidtest.database.entity.GenresString;

import timber.log.Timber;


public class Converter {
//    @TypeConverter
//    public static String fromNumbers(int [] numbers){
//        StringBuilder genreBuilder = new StringBuilder();
//
//        for (int count = 0; count<numbers.length; count++) {
//
//            for (Genre gen: GenreListProvider.getGenres()) {
//
//                if (numbers[count] == gen.getId()){
//                        genreBuilder.append(gen.getName());
//                }
//
//            }
//            if ((count < (numbers.length-1))){
//                genreBuilder.append("/");
//            }
//        }
//        return genreBuilder.toString();
//    }

//    //TODO Fix this TypeConverter
//    @TypeConverter
//    public static int [] fromGenreString(String genre){
//        Timber.d("Converter class  + " + genre) ;
//        return null;
//    }

    @TypeConverter
    public static String fromGenreString(GenresString string){
        return string.getGenreString();
    }

    @TypeConverter
    public static GenresString toGenreString(String string){
        return new GenresString(string);
    }


}
