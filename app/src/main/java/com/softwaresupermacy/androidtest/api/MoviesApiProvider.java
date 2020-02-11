package com.softwaresupermacy.androidtest.api;

import com.google.gson.GsonBuilder;
import com.softwaresupermacy.androidtest.database.entity.GenresString;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviesApiProvider {
    private static MoviesApi sInstance;

    private MoviesApiProvider() {}

    public synchronized static MoviesApi getMoviesApiInstance() {
        if (sInstance == null){
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(GenresString.class, new GenreStringDeserializer());

            sInstance = new Retrofit.Builder()
                    .baseUrl(MoviesApi.BASE_MOVIES_URL)
                    .addConverterFactory(GsonConverterFactory.create(builder.create()))
                    .build()
                    .create(MoviesApi.class);
        }
        return sInstance;
    }
}
