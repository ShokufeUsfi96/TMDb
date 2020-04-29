package com.usefi.tmdb.features.DetailMovie

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.usefi.tmdb.base.BaseViewModel
import com.usefi.tmdb.pojo.DetailMoviesModel
import com.usefi.tmdb.repository.NetworkRepository
import com.usefi.tmdb.repository.local.LocalEntity
import com.usefi.tmdb.repository.local.movieDatabase
import com.usefi.tmdb.utils.api_key
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DetailViewModel(private val repository : NetworkRepository) : BaseViewModel() {
    private val disposable = CompositeDisposable()
    private val detailtData = MutableLiveData<DetailMoviesModel>()
    fun getDetailData() : LiveData<DetailMoviesModel> = detailtData

    private val isSaved = MutableLiveData<Boolean>()
    fun getIsSaved() : LiveData<Boolean> = isSaved

    fun getmovieData(itemId: Int) {
        disposable.add(
            repository.getDetail(itemId, api_key)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    detailtData.value = it
                },{
                    Log.d("MYTAG", it.message.toString())
                })
        )

    }

    fun isMovieSaved(itemId: Int, context: Context) {
        val db = movieDatabase.getDbInstance(context)
        disposable.add(
            db?.getMovieDao()?.checkSavedOrNot(itemId)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    isSaved.value = it as Boolean
                },{
                    Log.d("MYTAG", it.message.toString())
                })!!
        )
    }
    fun vmInsert(context: Context) {
        val movieObj = LocalEntity(detailtData.value?.id, detailtData.value?.title,
            detailtData.value?.overview,detailtData.value?.voteAverage.toString(),
            detailtData.value?.releaseDate.toString(), detailtData.value?.posterPath.toString()
        ,detailtData.value?.backdropPath.toString(), detailtData.value?.runtime.toString(),
        detailtData.value?.genres!![0].name, detailtData.value?.voteCount.toString())
        val db = movieDatabase.getDbInstance(context)
        disposable.add(
            db?.getMovieDao()?.insertMovie(movieObj)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    Toast.makeText(context, "movie is saved.",Toast.LENGTH_SHORT).show()
                    Log.d("MYTAG", "saved.")
                    isSaved.value = true
                },{
                    Toast.makeText(context, it.message.toString(),Toast.LENGTH_SHORT).show()
                    isSaved.value = false
                })!!
        )
    }
    fun vmDelete(context: Context) {
        val db = movieDatabase.getDbInstance(context)
        disposable.add(
            db?.getMovieDao()?.deleteMovie(detailtData.value?.id)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    Toast.makeText(context, "movie is removed.", Toast.LENGTH_SHORT).show()
                    Log.d("MYTAG","removed.")
                    isSaved.value = false
                },{
                    Log.d("MYTAG",it.message.toString())
                })!!
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }


}