package com.softwaresupermacy.androidtest.api;

import android.net.Uri;

import java.net.MalformedURLException;
import java.net.URL;

public class NetworkingUtil
{
    private static final String BASE_PHOTO_URL = "https://image.tmdb.org/t/p/";
    public static final String POSTER_IMAGE_W500 = "w500";
    /**
     * @param movieImagePath append the movie path to the url
     * @param quality select the quality of the photo
     * @return the url which has been built by those parameters
     */
    public static URL buildPhotoURL(String movieImagePath, String quality)
    {
        Uri movieUri = Uri.parse(BASE_PHOTO_URL)
                .buildUpon()
                .appendEncodedPath(quality)
                .appendEncodedPath(movieImagePath)
                .build();
        try
        {
            return new URL(movieUri.toString());
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        return null;
    }


}
