package com.example.popularmovies

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.popularmovies.API.Movie
import com.example.popularmovies.API.MovieRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel (application: Application) : AndroidViewModel (application) {

    private val movieRepository = MovieRepository();
    val movie = MutableLiveData<Movie>();
    val error = MutableLiveData<String>();

    /**
     * Get movie information from the repository using Retrofit.
     * onResponse if the response is successful populate the [movie] object.
     * If the call encountered an error then populate the [error] object.
     */

    fun getMovie() {
        movieRepository.getMovie().enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.isSuccessful) movie.value = response.body()
                else error.value = "An error occurred: ${response.errorBody().toString()}"
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                error.value = t.message
            }
        })
    }


}