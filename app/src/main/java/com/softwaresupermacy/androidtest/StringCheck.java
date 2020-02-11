package com.softwaresupermacy.androidtest;



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
    //Todo complete this method to provide a consistent  width
//    public static String genreFixer(String s){
//        StringBuilder builder = new StringBuilder();
//     }
}
