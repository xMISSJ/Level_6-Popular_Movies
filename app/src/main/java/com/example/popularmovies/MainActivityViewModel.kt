package com.example.popularmovies

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.popularmovies.API.*
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel (application: Application) : AndroidViewModel (application) {

    private val movieRepository = MovieRepository();

    val movies = MutableLiveData<List<Movie>>();
    val error = MutableLiveData<String>();

    /**
     * Get movie information from the repository using Retrofit.
     * onResponse if the response is successful populate the [movies] object.
     * If the call encountered an error then populate the [error] object.
     */

    fun getMovies(year: String) {
        movieRepository.getPopularMovies(year).enqueue(object: Callback<MoviesResponse> {

            override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {

                if (response.isSuccessful) {
                    // resultMovie is variable from the MoviesResponse class.
                    movies.value = response.body()?.resultMovie
                    Log.d("MyDebug","Success");
                }
                else error.value = "An error occurred: ${response.errorBody().toString()}"
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                error.value = t.message;
                Log.d("MyDebug","Failure");
            }
        })
    }

}