package com.usefi.tmdb.features.HomePage

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.usefi.tmdb.R
import com.usefi.tmdb.base.BaseActivity
import com.usefi.tmdb.features.DetailMovie.DetailActivity
import com.usefi.tmdb.features.FavoritesMovie.FavoritesFragment
import com.usefi.tmdb.features.SearchMovie.SearchFragment
import com.usefi.tmdb.features.SearchMovie.SearchViewModel
import com.usefi.tmdb.features.SearchMovie.movieRecyclerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.search_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : BaseActivity() {

    private val vm : SearchViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction().replace(R.id.content_frame, HomeFragment())
                .commit()
        }

        bottomNav.setOnNavigationItemSelectedListener {
            var selectedFragment : Fragment? = null
            when(it.itemId){
                R.id.item_search -> { selectedFragment = SearchFragment() }
                R.id.item_home -> { selectedFragment = HomeFragment() }
                R.id.item_favorite -> { selectedFragment = FavoritesFragment()}
            }
            if (selectedFragment != null) {
                supportFragmentManager.beginTransaction().replace(R.id.content_frame, selectedFragment)
                    .commit()
            }
            return@setOnNavigationItemSelectedListener true
        }




    }

}
