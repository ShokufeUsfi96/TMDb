package com.usefi.tmdb.features.HomePage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.usefi.tmdb.R
import com.usefi.tmdb.base.BaseFragment
import kotlinx.android.synthetic.main.home_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment() {
    private val vm : HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.getPopularsData()
        vm.getPopularsRes().observe(this, androidx.lifecycle.Observer {
            val popmovieAdapter = HomeRecyclerAdapter(it)
            popularRecycler.adapter = popmovieAdapter
        })

        vm.getNowPlayingData()
        vm.getPlayingRes().observe(this, Observer {
            val playingAdapter = HomeRecyclerAdapter(it)
            nowPlayingRecycler.adapter = playingAdapter
        })

    }
}