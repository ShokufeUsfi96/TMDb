package com.usefi.tmdb.features.HomePage

import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.usefi.tmdb.R
import com.usefi.tmdb.pojo.Result
import com.usefi.tmdb.utils.imageBaseURL
import kotlinx.android.synthetic.main.home_recycler_item.view.*
import kotlinx.android.synthetic.main.recycler_item_layout.view.*

class HomeRecyclerAdapter(val list: List<Result>) : RecyclerView.Adapter<HomeRecyclerAdapter.popMoviesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): popMoviesViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.home_recycler_item,parent,false)
        return popMoviesViewHolder(v)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: popMoviesViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    class popMoviesViewHolder(val item: View) : RecyclerView.ViewHolder(item) {
        fun onBind(result: Result) {
            Picasso.get().load(imageBaseURL + result.posterPath).into(item.imgPop)
            item.titlePop.text = result.title
            item.homeRate.text = result.voteAverage.toString()
        }

    }

}