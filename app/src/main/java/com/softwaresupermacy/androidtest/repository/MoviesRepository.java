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
import com.softwaresupermacy.androidtest.database.entity.PackagedMovie;
import com.softwaresupermacy.androidtest.repository.helpers.RepositoryUtil;

import java.io.IOException;
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



    public LiveData<List<PackagedMovie>> getPackagedMovie(String ...packs){
        MutableLiveData<List<PackagedMovie>> mutableLiveData = new MutableLiveData<>();
        AppExecutors.getInstance().diskIO().execute(()->{
            refreshMovies(packs);
            getPackagedMovies(mutableLiveData);
        });
        return mutableLiveData;
    }

    private void refreshMovies(String[] packs){
            boolean hasMovies = mDao.hasMovie() == null ? false : true;
            if (!hasMovies) {
                Timber.d("Start");
                List<Movie> movies = new ArrayList<>();

                for (int x = 0; x<packs.length; x++){
                    Timber.d("1");

                    try {
                        Response<MoviesList> m = MoviesApiProvider.getMoviesApiInstance().getMovies(packs[x], MoviesApi.API_KEY, MoviesApi.ENG_LANG_RESULT).execute();

                        movies.addAll(RepositoryUtil.assignPackageDataType(packs[x], m.body().getmMovieList()));
                    } catch (IOException e) {
                        Timber.e(e);
                        Timber.e("NETWORK ERROR");
                    }
                    if (x == (packs.length - 1)){
                        mDao.insertMovies(movies);
                    }
                }
                Timber.d("End");
            }
    }

    private void getPackagedMovies(MutableLiveData<List<PackagedMovie>> mutableLiveData) {
        List<String> storedPacks = mDao.getPackages();
        List<PackagedMovie> packagedMovies = new ArrayList<>();
        for (int xx = 0; xx < storedPacks.size(); xx++) {
            List<Movie> pass = mDao.getMoviesByPackages(storedPacks.get(xx));
            packagedMovies.add(new PackagedMovie(pass, storedPacks.get(xx)));
            if (xx == (storedPacks.size() - 1)) {
                mutableLiveData.postValue(packagedMovies);
            }
        }
        Timber.d("end");
    }


   /*

    private void refreshData(String ...packages){
        mExecutor.execute(()-> {
            boolean hasMovies = mDao.hasMovie() != null;

            if (!hasMovies){

                Timber.i("database is empty fetching latest data");
                List<Movie> movies = new ArrayList<>();

                //Todo check the different versions of for loop
                for (int i = 0; i < packages.length; i++){

                    int finalI = i;
                    mApiClient.getMovies(packages[i], MoviesApi.API_KEY, MoviesApi.ENG_LANG_RESULT).enqueue(new Callback<MoviesList>() {
                        @Override
                        public void onResponse(Call<MoviesList> call, Response<MoviesList> response) {
                            List<Movie> modifiedMovies = RepositoryUtil.assignPackageDataType(packages[finalI], response.body().getmMovieList());
                            movies.addAll(modifiedMovies);

                            if (finalI == (packages.length - 1)){
                                mExecutor.execute(()->
                                        mDao.insertMovies(movies));
                            }
                        }

                        @Override
                        public void onFailure(Call<MoviesList> call, Throwable t) { Timber.e(t); }
                    });
                }
            }

        });

    }

    */

   /*
    private LiveData<List<PackagedMovie>> getPackagesMovies() {
        Timber.d("1 enter method");
        MutableLiveData<List<PackagedMovie>> observedPackagedMovie = new MutableLiveData<>();
        List<PackagedMovie> packagedMovie = new ArrayList<>();

        List<String> packages = new ArrayList<>();

        mExecutor.execute(() -> {
            Timber.d("1 enter executor");
            packages.addAll( mDao.getPackages());
            for (String pack: packages){
                Timber.d(pack);
                List<Movie> movies = mDao.getMoviesByPackages(pack);
                packagedMovie.add(new PackagedMovie(movies, pack));
            }
            Timber.d("loc size"+packagedMovie.size());
            observedPackagedMovie.postValue(packagedMovie);
        });
        Timber.d("Get out");

        return observedPackagedMovie;
    }
    */

}
