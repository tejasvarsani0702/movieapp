package com.example.moviesappp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private List<Movie> movies;
    private Context context;

    public MovieAdapter(List<Movie> movies, Context context) {
        this.movies = movies;
        this.context = context;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.title.setText(movie.getTitle());
        holder.year.setText(String.valueOf(movie.getYear()));
        holder.genre.setText(movie.getGenre());

        // Load image from drawable using the poster name
        int posterResId = context.getResources().getIdentifier(
                movie.getPoster(), // Name of the image (e.g., "matrix_poster")
                "drawable", // Resource type
                context.getPackageName() // Package name
        );

        if (posterResId != 0) {
            // If the image exists in drawable, load it using Glide
            Glide.with(context)
                    .load(posterResId)
                    .placeholder(R.drawable.default_poster) // Placeholder image while loading
                    .error(R.drawable.default_poster) // Image to show if loading fails
                    .into(holder.poster);
        } else {
            // If the image does not exist, load the default poster
            Glide.with(context)
                    .load(R.drawable.default_poster)
                    .into(holder.poster);
        }
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView title, year, genre;
        ImageView poster;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleTextView);
            year = itemView.findViewById(R.id.yearTextView);
            genre = itemView.findViewById(R.id.genreTextView);
            poster = itemView.findViewById(R.id.posterImageView);
        }
    }
}