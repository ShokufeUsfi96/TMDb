package com.usefi.tmdb.features.MovieDetailActivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso
import com.usefi.tmdb.R
import com.usefi.tmdb.base.extensions.toMinute
import com.usefi.tmdb.pojo.DetailMoviesModel
import com.usefi.tmdb.repository.local.LocalEntity
import com.usefi.tmdb.repository.local.movieDatabase
import com.usefi.tmdb.utils.imageBaseURL
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private val vm : DetailViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val itemId = intent.getIntExtra("itemId",0)
        vm.getmovieData(itemId)


        vm.getDetailData().observe(this, androidx.lifecycle.Observer{
            setData(it)
        })

        btnSave.setOnClickListener {
            vm.getDetailData().observe(this, Observer{
                val movie = LocalEntity(it.id, it.title, it.overview)
            })

        }

    }

    private fun setData(it: DetailMoviesModel?) {
        Picasso.get().load(imageBaseURL + it?.backdropPath).into(dImage)
        dTitle.text = it?.title
        dTime.text = String().toMinute(it?.runtime.toString())
        dRate.text = it?.voteAverage.toString()
        dDate.text = it?.releaseDate
        dOverview.text = it?.overview
    }
}
