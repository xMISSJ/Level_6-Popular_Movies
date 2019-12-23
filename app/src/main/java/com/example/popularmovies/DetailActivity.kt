package com.example.popularmovies

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.popularmovies.API.Movie
import com.example.popularmovies.API.MovieApi
import kotlinx.android.synthetic.main.activity_detail.*


class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail);
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.title = "";

        initViews();
    }

    private fun initViews(){
        val movie: Movie = intent.getParcelableExtra(MOVIE);

        tvTitle.text = movie.title;
        tvRelease.text = movie.releaseDate;
        tvRating.text = movie.voteAverage.toString();
        tvOverview.text = movie.overview;

        // Poster and Backdrop.
        Glide.with(this).load(MovieApi.imageBaseUrl + movie.posterPath).into(ivPoster);
        Glide.with(this).load(MovieApi.imageBaseUrl + movie.backdropPath).into(ivBackdrop);
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            // Return to previous screen.
            android.R.id.home -> {
                finish();
                true;
            }
            else -> return super.onOptionsItemSelected(item);
        }
    }
}