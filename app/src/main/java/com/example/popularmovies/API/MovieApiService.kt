package com.example.popularmovies.API

import retrofit2.Call
import retrofit2.http.GET

// Service.
public interface MovieApiService {

    // The GET method needed to retrieve the movie's data.
    @GET("3/movie/550?api_key=f0fa9c9cc2005f6f66bff61af5faad51")
    fun getMovie(): Call<Movie>;
}