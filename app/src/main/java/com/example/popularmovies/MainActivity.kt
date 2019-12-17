package com.example.popularmovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.popularmovies.API.Movie
import com.example.popularmovies.API.MovieApi
import kotlinx.android.synthetic.main.activity_main.*

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
            viewModel.getPopularMovies(); // Get all the popular movies from the API.
        }

        initViews();
    }

    private fun initViews(){
        // Initialize the recycler view with a linear layout manager, adapter
        rvMovies.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false);
        rvMovies.adapter = movieAdapter;
        //createItemTouchHelper().attachToRecyclerView(rvMovies);

        MovieApi.createApi().getPopularMovies(api_key);
    }
}
