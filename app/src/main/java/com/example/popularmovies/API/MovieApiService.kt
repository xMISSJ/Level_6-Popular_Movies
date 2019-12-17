package com.example.popularmovies.API

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

// Service.
public interface MovieApiService {

    // The GET method needed to retrieve the popular movies.
    @GET("movie/popular")
    fun getPopularMovies(@Query ("api_key") api_key: String) : Call<List<Movie>>;
}