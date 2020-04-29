package com.usefi.tmdb.features.FavoritesMovie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.usefi.tmdb.R
import com.usefi.tmdb.base.BaseFragment
import com.usefi.tmdb.features.DetailMovie.DetailActivity
import kotlinx.android.synthetic.main.favorite_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class FavoritesFragment : BaseFragment() {
    private val vm : FavoritesViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.favorite_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val clickListener : (Int)->Unit={
            val intent2 = Intent(activity, FavoritesDetailActivity::class.java)
            intent2.putExtra("movieId", it)
            startActivity(intent2)
        }
        activity?.let {
            vm.loadAllFavorites(it)
        }
        vm.getAllFavorites().observe(this, Observer{
            val adapter = FavoriteMoviesAdapter(it, clickListener)
            favoritesRecycler.adapter = adapter
        })

    }
}