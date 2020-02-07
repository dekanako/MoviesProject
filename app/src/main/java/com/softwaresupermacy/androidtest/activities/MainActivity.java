package com.softwaresupermacy.androidtest.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import com.facebook.stetho.Stetho;
import com.softwaresupermacy.androidtest.BuildConfig;
import com.softwaresupermacy.androidtest.R;

import com.softwaresupermacy.androidtest.api.MoviesApi;
import com.softwaresupermacy.androidtest.database.MovieDatabase;
import com.softwaresupermacy.androidtest.database.entity.Movie;
import com.softwaresupermacy.androidtest.database.entity.PackagedMovie;
import com.softwaresupermacy.androidtest.databinding.ActivityMainBinding;
import com.softwaresupermacy.androidtest.viewmodels.MovieViewModel;


import java.util.List;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initTimber();
        initStetho();
        MovieViewModel model = new ViewModelProvider(this).get(MovieViewModel.class);

        model.getObservablePackages().observe(this, packagedMovies -> {
            Timber.d("Size " + packagedMovies.size());

        });

    }

    //getPackagedMovies Timber logging
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
