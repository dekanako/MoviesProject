package com.softwaresupermacy.androidtest;


import timber.log.Timber;

public class StringCheck
{
    public static String stringFixer(String x)
    {
        StringBuilder builder = new StringBuilder();
        if (x.length() > 17)
        {
            builder.append(x.substring(0,15));
            builder.append("...");
            return builder.toString();
        }
        return x;
    }
    public static String genreFixer(String s){
        StringBuilder builder = new StringBuilder();
        String[] genreArray = s.split("/");
        int leng = genreArray.length > 2 ? 2 : genreArray.length;

        for (int x = 0; x < leng; x++){
            builder.append(genreArray[x]);
            if (x != 1){
                builder.append('/');
            }
        }
        if (genreArray.length > 2){
            builder.append("...");
        }
        return builder.toString();
    }
}
