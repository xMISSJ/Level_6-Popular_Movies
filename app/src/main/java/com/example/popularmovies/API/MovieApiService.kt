package com.example.popularmovies.API

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

// Service.
public interface MovieApiService {

    // The GET method needed to retrieve movies
    @GET("/3/discover/movie?" +
            "api_key=f0fa9c9cc2005f6f66bff61af5faad51" +
            "&language=en-US" +
            "&sort_by=popularity.desc" +
            "&include_adult=false" +
            "&include_video=false" +
            "&page=1")
    fun getMovies(@Query("year") year: String): Call<MoviesResponse>
}