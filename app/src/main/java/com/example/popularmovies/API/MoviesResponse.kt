package com.example.popularmovies.API

import com.google.gson.annotations.SerializedName

class MoviesResponse (
    @SerializedName("results") var resultMovie: List<Movie>
)