package com.softwaresupermacy.androidtest.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.softwaresupermacy.androidtest.api.MoviesApi;
import com.softwaresupermacy.androidtest.database.entity.PackagedMovie;
import com.softwaresupermacy.androidtest.repository.MoviesRepository;

import java.util.List;

public class MovieViewModel extends AndroidViewModel {
    private LiveData<List<PackagedMovie>> mObservablePackages;
    public MovieViewModel(@NonNull Application application) {
        super(application);

        MoviesRepository repository = MoviesRepository.getInstance(application);

        mObservablePackages = repository.getPackagedMovie(MoviesApi.POPULAR_PATH,
                MoviesApi.TOP_RATED_PATH);
    }
    public LiveData<List<PackagedMovie>> getObservablePackages() {
        return mObservablePackages;
    }

}