package com.softwaresupermacy.androidtest.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.softwaresupermacy.androidtest.BuildConfig;
import com.softwaresupermacy.androidtest.R;
import com.softwaresupermacy.androidtest.api.MoviesApi;
import com.softwaresupermacy.androidtest.repository.MoviesRepository;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTimber();
        Timber.d("TEST");
        MoviesRepository.getInstance(getApplication()).initMovieFetching(MoviesApi.POPULAR_PATH,
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
}
