package com.softwaresupermacy.androidtest.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.softwaresupermacy.androidtest.api.MoviesApi;
import com.softwaresupermacy.androidtest.api.MoviesApiProvider;
import com.softwaresupermacy.androidtest.database.AppExecutors;
import com.softwaresupermacy.androidtest.database.MovieDatabase;
import com.softwaresupermacy.androidtest.database.dao.MoviesDao;
import com.softwaresupermacy.androidtest.database.entity.Movie;
import com.softwaresupermacy.androidtest.database.entity.MoviesList;
import com.softwaresupermacy.androidtest.repository.helpers.RepositoryUtil;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class MoviesRepository {
    private static MoviesRepository sInstance;
    private MoviesApi mApiClient;
    private MoviesDao mDao;
    private Executor mExecutor;

    private MoviesRepository(Application application) {
        this.mApiClient = MoviesApiProvider.getMoviesApiInstance();
        this.mDao = MovieDatabase.getInstance(application).dao();
        mExecutor = AppExecutors.getInstance().diskIO();
    }

    public synchronized static MoviesRepository getInstance(Application application){
        if (sInstance == null){
            sInstance = new MoviesRepository(application);
        }
        return sInstance;
    }

    public LiveData<List<Movie>> getLatestData(String ...packages){
        MutableLiveData<List<Movie>> observableMovies = new MutableLiveData<>();
        List<Movie> movies = new ArrayList<>();

        //Todo check the different versions of for loop
        for (int i = 0; i < packages.length; i++){

            int finalI = i;
            mApiClient.getMovies(packages[i], MoviesApi.API_KEY, MoviesApi.ENG_LANG_RESULT).enqueue(new Callback<MoviesList>() {
                @Override
                public void onResponse(@NotNull Call<MoviesList> call, @NotNull Response<MoviesList> response) {
                    List<Movie> modifiedMovies = RepositoryUtil.assignPackageDataType(packages[finalI], response.body().getmMovieList());
                    movies.addAll(modifiedMovies);

                    if (finalI == (packages.length - 1)){

                        mExecutor.execute(() ->
                                mDao.insertMovies(movies));

                        observableMovies.setValue(movies);
                    }
                }

                @Override
                public void onFailure(@NotNull Call<MoviesList> call, @NotNull Throwable t) { Timber.e(t); }
            });
        }
        return observableMovies;
    }

}
