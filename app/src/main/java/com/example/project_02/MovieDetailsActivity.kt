package com.example.project_02

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class MovieDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val title = intent.getStringExtra("title")
        val overview = intent.getStringExtra("overview")
        val posterPath = intent.getStringExtra("posterPath")

        val releaseDate = intent.getStringExtra("releaseDate")
        val rating = intent.getDoubleExtra("rating", 0.0)
        val popularity = intent.getDoubleExtra("popularity", 0.0)

        val titleText: TextView = findViewById(R.id.detailsTitle)
        val overviewText: TextView = findViewById(R.id.detailsOverview)
        val posterImage: ImageView = findViewById(R.id.detailsPoster)

        val releaseDateText: TextView =
            findViewById(R.id.detailsReleaseDate)

        val ratingText: TextView =
            findViewById(R.id.detailsRating)

        val popularityText: TextView =
            findViewById(R.id.detailsPopularity)

        titleText.text = title
        overviewText.text = overview

        releaseDateText.text = "Release Date: $releaseDate"
        ratingText.text = "Rating: $rating"
        popularityText.text = "Popularity: $popularity"

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500$posterPath")
            .into(posterImage)
    }
}