package com.softwaresupermacy.androidtest.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.facebook.stetho.Stetho;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.softwaresupermacy.androidtest.BuildConfig;
import com.softwaresupermacy.androidtest.R;
import com.softwaresupermacy.androidtest.api.GenresJson;
import com.softwaresupermacy.androidtest.api.MoviesApi;
import com.softwaresupermacy.androidtest.database.entity.GenreContainer;
import com.softwaresupermacy.androidtest.repository.MoviesRepository;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTimber();
        initStetho();
        initWebService();



    }
    //Todo remove the direct call from activity to repository
    private void initWebService() {
        MoviesRepository.getInstance(getApplication()).getLatestData(MoviesApi.POPULAR_PATH,
                MoviesApi.TOP_RATED_PATH,
                MoviesApi.UPCOMING_PATH).observe(this, movies ->
                Timber.d("movie data "+movies.get(0) + " size "+movies.size()));
    }

    //init Timber logging
    private void initTimber() {
        if (BuildConfig.DEBUG){
            Timber.plant(new Timber.DebugTree());
        }
    }
    private void initStetho(){
        if (BuildConfig.DEBUG){
            Stetho.initializeWithDefaults(this);
        }
    }
}
