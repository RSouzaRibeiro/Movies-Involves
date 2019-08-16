package com.rafaelsouza.moviesinvolves.view.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rafaelsouza.moviesinvolves.R
import com.rafaelsouza.moviesinvolves.repository.model.Movie
import com.rafaelsouza.moviesinvolves.view.activity.MovieDetailsActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie.view.*
import java.text.ParseException
import java.text.SimpleDateFormat

class MovieAdapter(val context: Context, val movies: ArrayList<Movie>): RecyclerView.Adapter<MovieAdapter.ViewHolder>()  {
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
        holder.itemView.txtDateRelease.text =formatDate(movie.releaseDate)

        Picasso.with(context)
            .load(context.getString(R.string.PATH_GET_IMAGE)+movie.imagePath)
            .placeholder(R.mipmap.placeholder_movie)
            .resize(160,240)
            .into(holder.itemView.movieIMG)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra(MovieDetailsActivity.MOVIE_ID, movie.id)
            context.startActivity(intent)


        }
    }


    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    private val SERVER_DATE_FORMAT = "yyyy-MM-dd"
    private val HUMAN_DATE_FORMAT = "dd/MM/yyyy"
    @Throws(ParseException::class)
    fun formatDate(date: String): String {

        val initDate = SimpleDateFormat(SERVER_DATE_FORMAT).parse(date)
        val formatter = SimpleDateFormat(HUMAN_DATE_FORMAT)

        return formatter.format(initDate)
    }
}