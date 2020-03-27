package com.usefi.tmdb.features.SearchActivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import com.usefi.tmdb.R
import com.usefi.tmdb.pojo.Result
import com.usefi.tmdb.utils.imageBaseURL
import kotlinx.android.synthetic.main.recycler_item_layout.view.*

public class movieRecyclerAdapter(val results : List<Result>, val clickListener: (Int)->Unit)
    : RecyclerView.Adapter<movieRecyclerAdapter.movieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): movieViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_layout,parent,false)
        return movieViewHolder(v, clickListener)
    }

    override fun getItemCount(): Int = results.size

    override fun onBindViewHolder(holder: movieViewHolder, position: Int) {
        holder.onBind(results[position])

    }

    class movieViewHolder(val item: View, val clickListener: (Int) -> Unit) : RecyclerView.ViewHolder(item) {
        fun onBind(res : Result) {
            Picasso.get().load(imageBaseURL + res.posterPath).into(item.itemImg)
            item.itemTitle.text = res.title
            item.rate.text = res.voteAverage.toString()
            item.date.text = res.releaseDate

            item.setOnClickListener{
                clickListener(res.id)
            }
        }
    }


}