package com.softwaresupermacy.androidtest.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.softwaresupermacy.androidtest.api.MoviesApi;
import com.softwaresupermacy.androidtest.database.entity.Movie;
import com.softwaresupermacy.androidtest.repository.MoviesRepository;

import java.util.List;

public class MovieViewModel extends AndroidViewModel {
    private MoviesRepository mRepository;
    private LiveData<List<Movie>> mObservableMovies;
    public MovieViewModel(@NonNull Application application) {
        super(application);
        mRepository = MoviesRepository.getInstance(application);
        mObservableMovies = mRepository.getData(MoviesApi.POPULAR_PATH,MoviesApi.TOP_RATED_PATH);
    }

    public LiveData<List<Movie>> getObservableMovies() {
        return mObservableMovies;
    }
}