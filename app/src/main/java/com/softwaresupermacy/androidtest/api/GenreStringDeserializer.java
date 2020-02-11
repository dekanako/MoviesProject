package com.softwaresupermacy.androidtest.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.softwaresupermacy.androidtest.GenreListProvider;
import com.softwaresupermacy.androidtest.database.entity.Genre;
import com.softwaresupermacy.androidtest.database.entity.GenresString;

import java.lang.reflect.Type;

public class GenreStringDeserializer implements JsonDeserializer<GenresString> {
    @Override
    public GenresString deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonArray array = json.getAsJsonArray();
        StringBuilder builder = new StringBuilder();

        for (int count = 0; count < array.size(); count++){

            for (Genre gen: GenreListProvider.getGenres()){
                if (array.get(count).getAsInt() == gen.getId()){
                    builder.append(gen.getName());
                }
            }

            if ((count < (array.size() - 1))){
                builder.append("/");
            }
        }
        return new GenresString(builder.toString());
    }
}
