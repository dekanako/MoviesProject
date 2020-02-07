package com.softwaresupermacy.androidtest.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.facebook.stetho.Stetho;
import com.softwaresupermacy.androidtest.BuildConfig;
import com.softwaresupermacy.androidtest.R;

import com.softwaresupermacy.androidtest.activities.adapter.MainListAdapter;
import com.softwaresupermacy.androidtest.databinding.ActivityMainBinding;
import com.softwaresupermacy.androidtest.viewmodels.MovieViewModel;




import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initTimber();
        initStetho();
        MovieViewModel model = new ViewModelProvider(this).get(MovieViewModel.class);
        binding.mainList.setLayoutManager(new LinearLayoutManager(this));
        model.getObservablePackages().observe(this, packagedMovies -> {
            Timber.d("Size " + packagedMovies.size());
            binding.mainList.setAdapter(new MainListAdapter(getBaseContext(), packagedMovies));
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
