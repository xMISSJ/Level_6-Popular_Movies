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
    @SerializedName("release_date") var releaseDate: String,
    @SerializedName("vote_average") var voteAverage: Double,
    @SerializedName("overview") var overview: String,
    @SerializedName("poster_path") var posterPath: String,
    @SerializedName("backdrop_path") var backdropPath: String
) : Parcelable
