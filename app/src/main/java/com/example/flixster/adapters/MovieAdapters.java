package com.example.flixster.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixster.Models.Movie;
import com.example.flixster.MovieDetailsActivity;
import com.example.flixster.R;

import org.parceler.Parcels;

import java.util.List;

public class MovieAdapters extends RecyclerView.Adapter<MovieAdapters.ViewHolder> {

    Context context;
    List<Movie> movies;

    public MovieAdapters(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(movieView);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById((R.id.tvOverview));
            ivPoster = itemView.findViewById((R.id.ivPoster));
            itemView.setOnClickListener(this);
        }

        public void bind(Movie movie) {
            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());
            String imageUrl;
            int placeholderPic, placeholderWidth, placeholderHeight;
            if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                imageUrl = movie.getBackdropPath();
                placeholderPic = R.drawable.flicks_backdrop_placeholder;
                placeholderWidth = 250;
                placeholderHeight = 140;
            }
            else{
                imageUrl = movie.getPosterPath();
                placeholderPic = R.drawable.flicks_movie_placeholder;
                placeholderWidth = 150;
                placeholderHeight = 225;
            }

            Glide.with(context)
                    .load(imageUrl)
                    .placeholder(placeholderPic)
                    .override(placeholderWidth, placeholderHeight)
                    .into(ivPoster);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION){
                Movie movie = movies.get(position);
                Intent intent = new Intent(context, MovieDetailsActivity.class);
                intent.putExtra(Movie.class.getSimpleName(), Parcels.wrap(movie));
                context.startActivity(intent);
            }
        }
    }

//imageUrl
}
