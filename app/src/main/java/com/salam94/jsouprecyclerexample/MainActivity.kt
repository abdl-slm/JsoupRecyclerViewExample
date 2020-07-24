package com.salam94.jsouprecyclerexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.salam94.jsouprecyclerexample.adapter.RecyclerViewAdapter
import com.salam94.jsouprecyclerexample.data.MovieListItem
import kotlinx.android.synthetic.main.activity_main.*
import org.jsoup.Jsoup
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        retrieveWebInfo()
    }

    private fun retrieveWebInfo(){
        thread{
            // network call, so run it in the background
            val doc =
                    Jsoup.connect("https://www.wildaboutmovies.com/2019_movies/")
                            .get()

            val movieGrid = doc.getElementsByClass("post-grid")
            val movieItems = movieGrid[0].getElementsByTag("a")

            val movieList = ArrayList<MovieListItem>()

            for(movieItem in movieItems){
                val movieName = movieItem.text()
                val movieImageUrl = movieItem.getElementsByTag("img")[0].absUrl("data-original").toString()
                movieList.add(MovieListItem(movieName, movieImageUrl))
            }

            // can't access UI elements from the background thread
            this.runOnUiThread{
                val recyclerViewAdapter = RecyclerViewAdapter(movieList)
                val linearLayoutManager = LinearLayoutManager(this)

                recyclerView.layoutManager = linearLayoutManager
                recyclerView.adapter = recyclerViewAdapter
            }
        }
    }
}