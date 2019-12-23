package com.example.popularmovies.API

import retrofit2.Call

class MovieRepository {
    private val movieApi: MovieApiService = MovieApi.createApi()

    fun getPopularMovies(year: String): Call<MoviesResponse> = movieApi.getMovies(year)
}