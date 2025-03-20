package com.example.moviesappp;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class JSONUtility {
    public static List<Movie> loadMovies(Context context) {
        List<Movie> movies = new ArrayList<>();
        try {
            InputStream is = context.getAssets().open("movies.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String jsonString = new String(buffer, StandardCharsets.UTF_8);

            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonMovie = jsonArray.getJSONObject(i);

                String title = jsonMovie.optString("title", "Unknown");
                int year = jsonMovie.has("year") ? jsonMovie.optInt("year", 0) : 0;
                String genre = jsonMovie.optString("genre", "Unknown Genre");
                String poster = jsonMovie.optString("poster", "default_poster");

                movies.add(new Movie(title, year, genre, poster));
            }
        } catch (Exception e) {
            Log.e("JSONUtility", "Error loading movies: " + e.getMessage());
        }
        return movies;
    }
}