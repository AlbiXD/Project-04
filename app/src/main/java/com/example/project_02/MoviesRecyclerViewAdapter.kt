package com.example.project_02

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MoviesRecyclerViewAdapter(
    private val movies: List<Movie>,
    private val onMovieClick: (Movie) -> Unit
) : RecyclerView.Adapter<MoviesRecyclerViewAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)

        return MovieViewHolder(view)
    }

    inner class MovieViewHolder(
        val mView: View
    ) : RecyclerView.ViewHolder(mView) {

        val mMovieTitle: TextView =
            mView.findViewById(R.id.movieTitle)

        val mMovieDescription: TextView =
            mView.findViewById(R.id.movieDescription)

        val mMoviePoster: ImageView =
            mView.findViewById(R.id.moviePoster)
    }

    override fun onBindViewHolder(
        holder: MovieViewHolder,
        position: Int
    ) {

        val movie = movies[position]

        holder.mMovieTitle.text = movie.title
        holder.mMovieDescription.text = movie.overview

        Glide.with(holder.mView)
            .load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
            .into(holder.mMoviePoster)

        holder.mView.setOnClickListener {
            onMovieClick(movie)
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}