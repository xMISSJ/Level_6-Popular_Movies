package com.example.popularmovies.API

import retrofit2.Call
import retrofit2.http.GET

// Service.
public interface MovieApiService {

    // The GET method needed to retrieve the popular movies.
    @GET("movie/popular")
    fun getMovie(): Call<Movie>;
}