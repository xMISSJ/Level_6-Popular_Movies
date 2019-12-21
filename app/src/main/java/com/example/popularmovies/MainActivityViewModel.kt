package com.example.popularmovies

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.popularmovies.API.Movie
import com.example.popularmovies.API.MovieApi
import com.example.popularmovies.API.MovieRepository
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel (application: Application) : AndroidViewModel (application) {

    private val movieRepository = MovieRepository()

    val movies = MutableLiveData<List<Movie>>();
    val error = MutableLiveData<String>();

    /**
     * Get movie information from the repository using Retrofit.
     * onResponse if the response is successful populate the [movies] object.
     * If the call encountered an error then populate the [error] object.
     */

    fun getMovies(year: String) {
        movieRepository.getPopularMovies(year).enqueue(object: Callback<List<Movie>> {

            override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
                if (response.isSuccessful) movies.value = response.body()
                else error.value = "An error occurred: ${response.errorBody().toString()}"
            }

            override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
                error.value = t.message
            }
        })
    }

}