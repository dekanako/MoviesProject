package com.softwaresupermacy.androidtest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.softwaresupermacy.androidtest.api.GenresJson;
import com.softwaresupermacy.androidtest.database.entity.Genre;
import com.softwaresupermacy.androidtest.database.entity.GenreContainer;

import java.util.List;

public class GenreListProvider {

    public static List<Genre> getGenres() {

        GenreContainer genres = new Gson().fromJson(GenresJson.GENRE_LIST,
                new TypeToken<GenreContainer>(){}.getType());

        return genres.getGenres();
    }

}
