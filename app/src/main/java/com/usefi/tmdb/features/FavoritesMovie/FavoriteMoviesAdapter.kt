package com.usefi.tmdb.features.FavoritesMovie


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.usefi.tmdb.R
import com.usefi.tmdb.repository.local.LocalEntity
import com.usefi.tmdb.utils.imageBaseURL
import kotlinx.android.synthetic.main.recycler_item_layout.view.*

class FavoriteMoviesAdapter(private val favoriteList : List<LocalEntity>, private val clickListener: (Int)->Unit) : RecyclerView.Adapter<FavoriteMoviesAdapter.FavoriteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_layout,parent,false)
        return FavoriteViewHolder(v, clickListener)
    }

    override fun getItemCount(): Int = favoriteList.size

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.onBind(favoriteList[position])
    }

    class FavoriteViewHolder(private val item: View, val clickListener: (Int) -> Unit) : RecyclerView.ViewHolder(item){
        fun onBind(movie: LocalEntity) {
            item.itemTitle.text = movie.title
            item.rate.text = movie.rate.toString()
            item.date.text = movie.date.toString()
            Picasso.get().load(imageBaseURL + movie.posterPath).into(item.itemImg)

            item.setOnClickListener{
                clickListener(movie.movie_id!!)
            }
        }


    }
}