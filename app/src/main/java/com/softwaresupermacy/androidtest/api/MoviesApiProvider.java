package com.softwaresupermacy.androidtest.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviesApiProvider {
    private static MoviesApi sInstance;

    private MoviesApiProvider() {
    }

    public static MoviesApi getMoviesApiInstance() {
        if (sInstance == null){
            sInstance = new Retrofit.Builder()
                    .baseUrl(MoviesApi.BASE_MOVIES_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(MoviesApi.class);
        }
        return sInstance;
    }
}
