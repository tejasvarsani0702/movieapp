package com.example.moviesappp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Movie> movies = JSONUtility.loadMovies(this);

        if (movies.isEmpty()) {
            Toast.makeText(this, "No movies found or error loading data", Toast.LENGTH_LONG).show();
        } else {
            movieAdapter = new MovieAdapter(movies, this); // Pass the context here
            recyclerView.setAdapter(movieAdapter);
        }
    }
}