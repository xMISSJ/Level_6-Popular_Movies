package com.example.popularmovies.API

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie (
    // Use @SerializedName variable to indicate that the snake_cased variable can be serialized to camelCased variable.
    // Use https://www.json2kotlin.com/ to generate GSON SerializedName mapping.

    // To use in adapter to sort the movies.
    var id: Int,

    @SerializedName("title") var title: String,
    @SerializedName("releaseDate") var releaseDate: String,
    @SerializedName("voteAverage") var voteAverage: Double,
    @SerializedName("overview") var overview: String,
    @SerializedName("posterPath") var posterPath: String,
    @SerializedName("backdropPath") var backdropPath: String
) : Parcelable {
    private val baseUrl = "https://image.tmdb.org/t/p/w500/"

    fun getPosterImage() = baseUrl + posterPath;
    fun getBackdropImage() = baseUrl + backdropPath;
}
