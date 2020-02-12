package com.softwaresupermacy.androidtest.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.softwaresupermacy.androidtest.api.MoviesApi;
import com.softwaresupermacy.androidtest.database.entity.Genre;
import com.softwaresupermacy.androidtest.database.entity.PackagedMovie;
import com.softwaresupermacy.androidtest.repository.MoviesRepository;

import java.util.List;


public class MovieViewModel extends AndroidViewModel {
    private LiveData<List<PackagedMovie>> mObservablePackages;
    private List<Genre> mGenres;
    private MoviesRepository mRepository;
    private String[] mPackages;
    private static final String TAG = "MovieViewModel";
    public MovieViewModel(@NonNull Application application, String ...packages) {
        super(application);

        mRepository = MoviesRepository.getInstance(application);
        this.mPackages = packages;
        Log.d(TAG, "ViewModel");


        mObservablePackages = mRepository.getPackagedMovie(packages);
        mGenres = mRepository.getGenresList();
    }

    public List<Genre> getGenres() {
        return mGenres;
    }

    public LiveData<List<PackagedMovie>> getObservablePackages() {
        return mObservablePackages;
    }


    public void refreshData(String ...packs) {
        mRepository.forceRefresh(packs);
    }
}