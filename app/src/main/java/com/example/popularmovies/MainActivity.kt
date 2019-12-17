package com.example.popularmovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.popularmovies.API.Movie
import com.example.popularmovies.API.MovieRepository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val movies = arrayListOf<Movie>();
    private val movieAdapter = MovieAdapter(movies);
    private val movieRepository = MovieRepository();

    private lateinit var viewModel: MainActivityViewModel;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        supportActionBar?.title = "Movie List";

        initViews();
    }

    private fun initViews(){
        // Initialize the recycler view with a linear layout manager, adapter
        rvMovies.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false);
        rvMovies.adapter = movieAdapter;
        //createItemTouchHelper().attachToRecyclerView(rvMovies);
    }
}
