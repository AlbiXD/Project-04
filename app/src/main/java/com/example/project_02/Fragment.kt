package com.example.project_02

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

private const val API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed"

class MoviesFragment : Fragment() {

    /*
     * Constructing the view
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(
            R.layout.fragment_movies_list,
            container,
            false
        )

        val recyclerView =
            view.findViewById<View>(R.id.list) as RecyclerView

        val context = view.context

        recyclerView.layoutManager =
            LinearLayoutManager(context)

        updateAdapter(recyclerView)

        return view
    }

    /*
     * Updates the RecyclerView adapter with new data.
     * This is where the networking happens.
     */
    private fun updateAdapter(recyclerView: RecyclerView) {

        val client = AsyncHttpClient()

        val url =
            "https://api.themoviedb.org/3/movie/popular?api_key=$API_KEY"

        client.get(url, object : JsonHttpResponseHandler() {

            override fun onSuccess(
                statusCode: Int,
                headers: Headers,
                json: JsonHttpResponseHandler.JSON
            ) {

                val results =
                    json.jsonObject.getJSONArray("results")

                val movies: MutableList<Movie> =
                    mutableListOf()

                for (i in 0 until results.length()) {

                    val movieJson =
                        results.getJSONObject(i)

                    val movie = Movie(
                        title = movieJson.getString("title"),
                        overview = movieJson.getString("overview"),
                        posterPath = movieJson.getString("poster_path"),
                        releaseDate = movieJson.getString("release_date"),
                        rating = movieJson.getDouble("vote_average"),
                        popularity = movieJson.getDouble("popularity")
                    )

                    movies.add(movie)
                }

                recyclerView.adapter =
                    MoviesRecyclerViewAdapter(movies) { movie ->

                        val intent = Intent(requireContext(), MovieDetailsActivity::class.java)

                        intent.putExtra("title", movie.title)
                        intent.putExtra("overview", movie.overview)
                        intent.putExtra("posterPath", movie.posterPath)

                        intent.putExtra("releaseDate", movie.releaseDate)
                        intent.putExtra("rating", movie.rating)
                        intent.putExtra("popularity", movie.popularity)

                        startActivity(intent)

                        startActivity(intent)
                    }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                t: Throwable?
            ) {
            }
        })
    }
}