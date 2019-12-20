package com.example.popularmovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.popularmovies.API.Movie
import com.example.popularmovies.API.MovieApi
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_movie.*

const val api_key = "f0fa9c9cc2005f6f66bff61af5faad51"

class MainActivity : AppCompatActivity() {

    private val movies = arrayListOf<Movie>();
    private val movieAdapter = MovieAdapter(movies);

    private lateinit var viewModel: MainActivityViewModel;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        supportActionBar?.title = "Movie List";

        btn_submit.setOnClickListener{
            onClick();
        }

        initViews();
        initViewModel();
    }

    private fun initViews(){
        // Initialize the recycler view with a linear layout manager, adapter
        rvMovies.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false);
        rvMovies.adapter = movieAdapter;
        //createItemTouchHelper().attachToRecyclerView(rvMovies);

        MovieApi.createApi().getPopularMovies(api_key);
    }

    private fun initViewModel(){
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        viewModel.movies.observe(this, Observer {
            movies.clear();
            movies.addAll(it);
            movieAdapter.notifyDataSetChanged();
        })
    }

    private fun onClick() {
        var submitYear = etYear.text.toString();
        val yearRange = 1900..2019
        if (submitYear.isNotBlank()) {
            if (submitYear.toInt() !in yearRange) {
                Toast.makeText(this, "Date must be between 1900 and 2019.", Toast.LENGTH_LONG)
                    .show();
            } else {
                Toast.makeText(this, "Load Movies", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Please fill in a date.", Toast.LENGTH_LONG).show();
        }
    }
}
