package com.rafaelsouza.moviesinvolves.view.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rafaelsouza.moviesinvolves.R
import com.rafaelsouza.moviesinvolves.repository.model.Movie
import com.rafaelsouza.moviesinvolves.util.Utils
import com.rafaelsouza.moviesinvolves.view.activity.MovieDetailsActivity
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie.view.*


class MovieAdapter(val context: Context, val movies: List<Movie>) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var movie = movies[position]

        holder.itemView.txtTitle.text = movie.title
        holder.itemView.txtDateRelease.text = Utils().formatDate(movie.releaseDate)

        Picasso.with(context)
            .load(context.getString(R.string.PATH_GET_IMAGE) + movie.imagePath)
            .placeholder(R.mipmap.placeholder_movie)
            .networkPolicy(NetworkPolicy.NO_STORE)
            .resize(160, 240)
            .into(holder.itemView.movieIMG)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra(MovieDetailsActivity.MOVIE_ID, movie.id)
            context.startActivity(intent)


        }
    }

    fun add(listmovie: List<Movie>){
        (movies as ArrayList).addAll(listmovie)
        notifyDataSetChanged()

    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


}