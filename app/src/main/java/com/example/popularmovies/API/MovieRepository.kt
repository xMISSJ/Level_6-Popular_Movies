package com.example.popularmovies.API

class MovieRepository {
    private val movieApi: MovieApiService = MovieApi.createApi()

    fun getMovie() = movieApi.getMovie();
}