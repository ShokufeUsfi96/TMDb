package com.usefi.tmdb.features.DetailMovie

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.Layout.JUSTIFICATION_MODE_INTER_WORD
import android.util.Log
import android.util.TypedValue
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso
import com.usefi.tmdb.R
import com.usefi.tmdb.R.string.add_to_favorite
import com.usefi.tmdb.R.string.addedToFavorite
import com.usefi.tmdb.base.extensions.toMinute
import com.usefi.tmdb.pojo.DetailMoviesModel
import com.usefi.tmdb.utils.imageBaseURL
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.android.viewmodel.ext.android.viewModel


class DetailActivity : AppCompatActivity() {

    private val vm : DetailViewModel by viewModel()

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val itemId = intent.getIntExtra("itemId",0)
        vm.getmovieData(itemId)

        vm.isMovieSaved(itemId,this)
        vm.getIsSaved().observe(this,Observer{
            if (it == true){
                btnSave.text = getString(addedToFavorite)
                btnSave.setBackgroundResource(R.drawable.button_style_saved)
                btnSave.setTextColor(getColor(R.color.gold))
                Log.d("MYTAG","movie exists in DB")
            }else{
                btnSave.text = getString(R.string.add_to_favorite)
                btnSave.setBackgroundResource(R.drawable.button_style)
                btnSave.setTextColor(getColor(R.color.black))
                Log.d("MYTAG","movie doesn't exist in DB")
            }
        })

        vm.getDetailData().observe(this, androidx.lifecycle.Observer{
            setData(it)
        })

        btnSave.setOnClickListener {
            when (btnSave.text){
                getString(addedToFavorite) -> {
                vm.vmDelete(this)
                    btnSave.text = getString(R.string.add_to_favorite)
                    btnSave.setBackgroundResource(R.drawable.button_style)
                    btnSave.setTextColor(getColor(R.color.black))
            }
                getString(add_to_favorite) ->{
                vm.vmInsert(this)
                    btnSave.text = getString(R.string.addedToFavorite)
                    btnSave.setBackgroundResource(R.drawable.button_style_saved)
                    btnSave.setTextColor(getColor(R.color.gold))
            }
                }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setData(it: DetailMoviesModel?) {
        Picasso.get().load(imageBaseURL + it?.backdropPath).into(dImage)
        Picasso.get().load(imageBaseURL + it?.posterPath).into(dPoster)
        dTitle.text = it?.title
        dTime.text = String().toMinute(it?.runtime.toString())
        dRate.text = it?.voteAverage.toString()
        votes.text = it?.voteCount.toString() + " votes"
        ratingbar.rating = it?.voteAverage!!/2
        dDate.text = it.releaseDate
        dOverview.text = it.overview
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            dOverview.justificationMode = JUSTIFICATION_MODE_INTER_WORD
        }
        getGenres(it)


    }

    private fun getGenres(it: DetailMoviesModel?) {
        val genresSize = it?.genres?.size
        if (genresSize != null) {
            for (i in 0 until (genresSize - 1)) {
                val genreName = TextView(this)
                val params = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT)
                params.setMargins(0,0,10,0)
                genreName.layoutParams = params
                genreName.setPadding(8,0,8,0)
                genreName.text = it.genres[i].name
                genreName.setTextColor(Color.WHITE)
                genreName.setBackgroundResource(R.drawable.genres_style)
                genreName.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                    resources.getDimension(R.dimen.result_font))
                genresList.addView(genreName)

            }
        }
    }
}
