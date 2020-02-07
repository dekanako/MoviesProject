package com.softwaresupermacy.androidtest.api;

import com.softwaresupermacy.androidtest.database.entity.MoviesList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MoviesApi {
    String BASE_MOVIES_URL = "https://api.themoviedb.org/3/movie/";

    String POPULAR_PATH = "popular";
    String TOP_RATED_PATH = "top_rated";
    String UPCOMING_PATH = "upcoming";
    String NOW_PLAYING  = "now_playing";

    String ENG_LANG_RESULT = "en-US";
    String API_KEY = "90429cbb0771760ab50be543df397f62";

    @GET("{path_name}")
    Call<MoviesList> getMovies(@Path("path_name")String searchType,
                               @Query("api_key")String apiKey,
                               @Query("language")String language);

}
