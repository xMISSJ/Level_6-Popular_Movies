package com.example.popularmovies.API

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Client.
class MovieApi {
    companion object {
        // The base URL of the API & Image.
        private const val baseUrl = "https://api.themoviedb.org/3/";
        const val imageBaseUrl = "https://image.tmdb.org/t/p/w500/";

        /**
         * @return [MovieApiService] The service class off the retrofit client.
         */
        fun createApi(): MovieApiService {
            // Create an OkHttpClient to be able to make a log of the network traffic
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

            // Create the Retrofit instance
            val movieApi = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

            // Return the Retrofit NumbersApiService
            return movieApi.create(MovieApiService::class.java);
        }
    }
}