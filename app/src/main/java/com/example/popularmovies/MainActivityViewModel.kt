package com.example.popularmovies

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.popularmovies.API.Movie
import com.example.popularmovies.API.MovieApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel (application: Application) : AndroidViewModel (application) {

    val movie = MutableLiveData<List<Movie>>();
    val error = MutableLiveData<String>();
    val api_key = "f0fa9c9cc2005f6f66bff61af5faad51"

    /**
     * Get movie information from the repository using Retrofit.
     * onResponse if the response is successful populate the [movie] object.
     * If the call encountered an error then populate the [error] object.
     */

    fun getPopularMovies() {
        MovieApi.createApi().getPopularMovies(api_key).enqueue(object : Callback<List<Movie>> {
            override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
                if (response.isSuccessful) movie.value = response.body()
                else error.value = "An error occurred: ${response.errorBody().toString()}"
            }

            override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
                error.value = t.message
            }
        })
    }


}