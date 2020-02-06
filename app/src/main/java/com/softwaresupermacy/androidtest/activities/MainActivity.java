package com.softwaresupermacy.androidtest.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.facebook.stetho.Stetho;
import com.softwaresupermacy.androidtest.BuildConfig;
import com.softwaresupermacy.androidtest.R;
import com.softwaresupermacy.androidtest.viewmodels.MovieViewModel;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTimber();
        initStetho();

        MovieViewModel model = new ViewModelProvider(this).get(MovieViewModel.class);
        model.getObservableMovies().observe(this, movies ->
                Timber.d(movies.size() + " SIZE"));

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
