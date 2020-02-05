package com.softwaresupermacy.androidtest.repository.helpers;

import com.softwaresupermacy.androidtest.database.entity.Movie;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class RepositoryUtil {

    public static List<Movie> assignPackageDataType(String moviePackage, List<Movie> movies) {
        List<Movie> packagedMovieList = new ArrayList<>();
        Timber.d(moviePackage);
        for (Movie movie: movies){
            packagedMovieList.add(new Movie(movie.getImageLink(), movie.getFilmTitle(), movie.getDbMovieId(),movie.getOverView(), moviePackage, movie.getGenreIds()));
        }
        Timber.d(packagedMovieList.get(0).toString());
        return packagedMovieList;
    }
}
