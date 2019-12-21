package com.example.popularmovies

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.popularmovies.API.Movie
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail);
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.title = "";

        initViews();
    }

    fun initViews(){
        val movie: Movie = intent.getParcelableExtra(MOVIE);

        tvOverview.movementMethod = ScrollingMovementMethod();
        tvOverview.text = movie.overview;
        tvRating.text = movie.voteAverage.toString();
        tvRelease.text = movie.releaseDate;
        tvTitle.text = movie.title;

        Glide.with(this).load(movie.getPosterImage()).into(ivPoster);
        Glide.with(this).load(movie.getBackdropImage()).into(ivBackdrop);
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