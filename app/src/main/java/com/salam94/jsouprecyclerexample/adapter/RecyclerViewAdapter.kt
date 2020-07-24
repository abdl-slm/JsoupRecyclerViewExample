package com.salam94.jsouprecyclerexample.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.salam94.jsouprecyclerexample.R
import com.salam94.jsouprecyclerexample.data.MovieListItem
import com.salam94.ptcgdex.util.inflate
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_list.view.*


class RecyclerViewAdapter(private val movieList: List<MovieListItem>) :
    RecyclerView.Adapter<RecyclerViewAdapter.SetHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SetHolder {
        val inflatedView: View = parent.inflate(R.layout.item_list, false)
        return SetHolder(
            inflatedView
        )
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: SetHolder, position: Int) {
        val itemCard = movieList[position]
        holder.bindSet(itemCard)
    }


    class SetHolder(v: View) : RecyclerView.ViewHolder(v),
        View.OnClickListener {
        private var view: View = v
        private var movieList: MovieListItem? = null

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View) {


        }


        fun bindSet(movieList: MovieListItem) {
            this.movieList = movieList

            view.titleMovie.text = movieList.name
            Picasso.get().load(movieList.imageUrl).into(view.imgMovie)
        }
    }

}