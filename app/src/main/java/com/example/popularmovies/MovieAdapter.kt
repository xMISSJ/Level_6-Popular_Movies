package com.example.popularmovies

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.popularmovies.API.Movie
import com.example.popularmovies.API.MovieApi
import kotlinx.android.synthetic.main.item_movie.view.*

/*
 * An ArrayList of Movie objects is added to the class constructor
 * so the RecyclerView knows which Movie objects it needs to display.
 */
class MovieAdapter (private val movies: List<Movie>, private val onClick: (Movie) -> Unit) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    /*
        *  For the context variable the lateinit declaration has been used to let Kotlin
        *  know that this variable will be initialized later (in the onCreateViewHolder method).
        */
    lateinit var context: Context;
    private val imageBaseUrl = MovieApi.imageBaseUrl;

    /*
     * In onCreateViewHolder a ViewHolder object is created which inflates the layout file we created (item_portal.xml).
     * We will be needing Context later on so a variable context is set.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context;

        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
        );
    }

    // Size of Movies.
    override fun getItemCount(): Int {
        return movies.size;
    }

    // Bind method to bind the data to the ViewHolder.
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position]);
    }

    /*
     * The ViewHolders bind method uses kotlin synthetics to get the
     * references from the layout file for the ImageView and TextView.
     */
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener{
                // adapterPosition is position of the item represented by the ViewHolder.
                onClick(movies[adapterPosition]);
            }
        }

        fun bind(movie: Movie) {
            itemView.tvNumber.text = "${(adapterPosition + 1)}.";
            Glide.with(context).load(imageBaseUrl + movie.posterPath).into(itemView.ivMovie);
        }
    }
}
