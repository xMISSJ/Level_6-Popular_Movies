package com.example.popularmovies

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.popularmovies.API.Movie
import com.example.popularmovies.API.MovieApi
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_movie.*

const val api_key = "f0fa9c9cc2005f6f66bff61af5faad51"
const val MOVIE = "MOVIE"

class MainActivity : AppCompatActivity() {

    private val movies = arrayListOf<Movie>();
    private val movieAdapter = MovieAdapter(movies) { movie ->
        startDetailActivity(movie);
    }

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
        // Initialize the recycler view with a linear layout manager, adapter.
        rvMovies.layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL);
        rvMovies.adapter = movieAdapter;
    }

    private fun initViewModel(){
        // Observe movies from view model. When data has changed, update list.
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        viewModel.movies.observe(this, Observer {
            this@MainActivity.movies.clear();
            this@MainActivity.movies.addAll(it);
            movieAdapter.notifyDataSetChanged();
        })
        viewModel.error.observe(this, Observer {
            Log.d("MyDebug", it)
        })
    }

    private fun onClick() {

        hideKeyboard(this.rvMovies)

        var submitYear = etYear.text.toString();
        val yearRange = 1900..2019;

        // Checks whether input isn't empty and has a valid year.
        if (submitYear.isNotBlank()) {
            if (submitYear.toInt() !in yearRange) {
                Toast.makeText(this, "Date must be between 1900 and 2019.", Toast.LENGTH_LONG).show();
                // If so, add the movies from the submitted year.
            } else {
                viewModel.getMovies(submitYear)
            }
        } else {
            Toast.makeText(this, "Please fill in a date.", Toast.LENGTH_LONG).show();
        }
    }

    // Activity is started on click of one of the items in the adapter. This is handled
    fun startDetailActivity(movie: Movie){
        val intent = Intent(this@MainActivity, DetailActivity::class.java)
        intent.putExtra(MOVIE, movie)
        startActivity(intent)
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
