package com.usefi.tmdb.features.FavoritesMovie

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.util.TypedValue
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso
import com.usefi.tmdb.R
import com.usefi.tmdb.base.BaseActivity
import com.usefi.tmdb.base.extensions.toMinute
import com.usefi.tmdb.features.DetailMovie.DetailViewModel
import com.usefi.tmdb.repository.local.LocalEntity
import com.usefi.tmdb.utils.imageBaseURL
import kotlinx.android.synthetic.main.activity_favorites_detail.*
import org.koin.android.viewmodel.ext.android.viewModel

class FavoritesDetailActivity : BaseActivity() {

    private val vm: FavoritesViewModel by viewModel()
    private val vmDetail: DetailViewModel by viewModel()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites_detail)

        val movieId = intent.getIntExtra("movieId", 0)
        vm.getDetailFromDB(movieId, this)

        vm.getDetailData().observe(this, Observer {
            setData(it)
        })

        btnSave2.setOnClickListener {
            when (btnSave2.text) {
                getString(R.string.addedToFavorite) -> {
                    vmDetail.vmDelete(this)
                    btnSave2.text = getString(R.string.add_to_favorite)
                    btnSave2.setBackgroundResource(R.drawable.button_style)
                    btnSave2.setTextColor(getColor(R.color.black))
                }
                getString(R.string.add_to_favorite) -> {
                    vmDetail.vmInsert(this)
                    btnSave2.text = getString(R.string.addedToFavorite)
                    btnSave2.setBackgroundResource(R.drawable.button_style_saved)
                    btnSave2.setTextColor(getColor(R.color.gold))
                }
            }
        }

    }

        @SuppressLint("SetTextI18n")
        private fun setData(it: LocalEntity?) {
            Picasso.get().load(imageBaseURL + it?.backdropPath).into(dImage2)
            Picasso.get().load(imageBaseURL + it?.posterPath).into(dPoster2)
            dTitle2.text = it?.title
            dTime2.text = String().toMinute(it?.time.toString())
            dRate2.text = it?.rate.toString()
            votes2.text = it?.votes + " votes"
            ratingbar2.rating = (it?.rate?.toFloat()!!) / 2
            dDate2.text = it.date
            dOverview2.text = it.overView
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                dOverview2.justificationMode = Layout.JUSTIFICATION_MODE_INTER_WORD
            }
            getGenre(it.genre)
        }

        private fun getGenre(it: String) {
            val genreName2 = TextView(this)
            val params = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(0, 0, 10, 0)
            genreName2.layoutParams = params
            genreName2.setPadding(8, 0, 8, 0)
            genreName2.text = it
            genreName2.setTextColor(Color.WHITE)
            genreName2.setBackgroundResource(R.drawable.genres_style)
            genreName2.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimension(R.dimen.result_font)
            )
            genresList2.addView(genreName2)

        }



}

