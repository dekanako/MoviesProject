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
import com.softwaresupermacy.androidtest.api.MoviesApi;
import com.softwaresupermacy.androidtest.databinding.ActivityMainBinding;
import com.softwaresupermacy.androidtest.viewmodels.MovieViewModel;
import com.softwaresupermacy.androidtest.viewmodels.MovieViewModelFactory;


import timber.log.Timber;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mBinding;
    private MainListAdapter mMainListAdapter;
    private MovieViewModel mModel;

    //requested Movies packages
    private static String[]requestedPackages = new String[]{MoviesApi.NOW_PLAYING,
            MoviesApi.TOP_RATED_PATH,
            MoviesApi.UPCOMING_PATH};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //initializing the views and the lists
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mMainListAdapter = new MainListAdapter(this);
        mBinding.mainList.setAdapter(mMainListAdapter);
        mBinding.mainList.setLayoutManager(new LinearLayoutManager(this));
        mBinding.swipeLayout.setRefreshing(true);

        initTimber();
        initStetho();

        mModel = new ViewModelProvider(this,new MovieViewModelFactory(getApplication(), requestedPackages))
                .get(MovieViewModel.class);

        mModel.getObservablePackages().observe(this, movieList -> {
            mMainListAdapter.setMovies(movieList, mModel.getGenres());
            mBinding.swipeLayout.setRefreshing(false);
        });

        mBinding.swipeLayout.setOnRefreshListener(()-> {
            mMainListAdapter.clear();
            mModel.refreshData(requestedPackages);
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
