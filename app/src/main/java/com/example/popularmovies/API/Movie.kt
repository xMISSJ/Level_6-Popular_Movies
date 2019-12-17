package com.example.popularmovies.API

import com.google.gson.annotations.SerializedName

data class Movie (
    // Use @SerializedName variable to indicate that the snake_cased variable can be serialized to camelCased variable.
    // Use https://www.json2kotlin.com/ to generate GSON SerializedName mapping.
    @SerializedName("id") var id: Int,
    @SerializedName("voteAverage") var voteAverage: Double,
    @SerializedName("voteCount") var voteCount: Int,
    @SerializedName("originalTitle") var originalTitle: String,
    @SerializedName("title") var tile: String,
    @SerializedName("popularity") var popularity: Double,
    @SerializedName("backdropPath") var backdropPath: String,
    @SerializedName("overview") var overview: String,
    @SerializedName("releaseDate") var releaseDate: String,
    @SerializedName("posterPath") var posterPath: String
)