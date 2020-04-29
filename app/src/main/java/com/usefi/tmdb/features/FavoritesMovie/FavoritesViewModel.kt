package com.usefi.tmdb.features.FavoritesMovie

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.usefi.tmdb.base.BaseViewModel
import com.usefi.tmdb.repository.local.LocalEntity
import com.usefi.tmdb.repository.local.movieDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class FavoritesViewModel : BaseViewModel() {
    private val disposable = CompositeDisposable()
    private val allFavorites = MutableLiveData<List<LocalEntity>>()
    fun getAllFavorites() : LiveData<List<LocalEntity>>  = allFavorites

    private  val detailData = MutableLiveData<LocalEntity>()
    fun getDetailData() : LiveData<LocalEntity> = detailData

    fun loadAllFavorites(context: Context) {
        val db = movieDatabase.getDbInstance(context)
        disposable.add(
            db?.getMovieDao()?.getAllMovies()
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    allFavorites.value = it
                },{
                    Log.d("MYTAG", it.message.toString())
                })!!

        )
    }

    fun getDetailFromDB(movieId: Int, context: Context) {
        val db = movieDatabase.getDbInstance(context)
        disposable.add(
            db?.getMovieDao()?.getMovieByID(movieId)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    detailData.value = it
                },{
                    Log.d("MYTAG", it.message.toString())
                })!!
        )

    }

}