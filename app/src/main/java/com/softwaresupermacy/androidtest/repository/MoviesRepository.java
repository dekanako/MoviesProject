package com.softwaresupermacy.androidtest.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.softwaresupermacy.androidtest.api.MoviesApi;
import com.softwaresupermacy.androidtest.api.MoviesApiProvider;
import com.softwaresupermacy.androidtest.database.entity.Movie;
import com.softwaresupermacy.androidtest.database.entity.MoviesList;
import com.softwaresupermacy.androidtest.repository.helpers.RepositoryUtil;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class MoviesRepository {
    private static MoviesRepository sInstance;
    private MoviesApi mApiClient;
    private Application application;

    private MoviesRepository(Application application) {
        this.mApiClient = MoviesApiProvider.getMoviesApiInstance();
        this.application = application;
    }

    public static MoviesRepository getInstance(Application application){
        if (sInstance == null){
            sInstance = new MoviesRepository(application);
        }
        return sInstance;
    }

    public LiveData<List<Movie>> initMovieFetching(String ...packages){
        MutableLiveData<List<Movie>> observableMovies = new MutableLiveData<>();

        List<Movie> movies = new ArrayList<>();
        //Todo check the different versions of for loop
        for (String moviePackage: packages){
            mApiClient.getMovies(moviePackage, MoviesApi.API_KEY, MoviesApi.ENG_LANG_RESULT).enqueue(new Callback<MoviesList>() {
                @Override
                public void onResponse(@NotNull Call<MoviesList> call, @NotNull Response<MoviesList> response) {
                    List<Movie> modifiedMovies = RepositoryUtil.assignPackageDataType(moviePackage, response.body().getmMovieList());
                    movies.addAll(modifiedMovies);
                    observableMovies.setValue(movies);
                }

                @Override
                public void onFailure(@NotNull Call<MoviesList> call, @NotNull Throwable t) { Timber.e(t); }
            });
        }
        return observableMovies;
    }

}
