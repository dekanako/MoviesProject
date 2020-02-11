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
            packagedMovieList.add(new Movie(movie.getImageLink(),
                    movie.getFilmTitle(),
                    movie.getDbMovieId(),
                    movie.getOverView(), moviePackage,
                    movie.getGenresString()));
        }
        Timber.d(packagedMovieList.get(0).toString());
        return packagedMovieList;
    }

    public static String capitizeString(String name){
        String captilizedString="";
        if(!name.trim().equals("")){
            captilizedString = name.substring(0,1).toUpperCase() + name.substring(1);
        }
        return captilizedString;
    }
}
