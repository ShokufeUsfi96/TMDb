package com.usefi.tmdb.features.MovieDetailActivity

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.usefi.tmdb.base.BaseViewModel
import com.usefi.tmdb.pojo.DetailMoviesModel
import com.usefi.tmdb.repository.NetworkRepository
import com.usefi.tmdb.utils.api_key
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DetailViewModel(private val repository : NetworkRepository) : BaseViewModel() {
    private val disposable = CompositeDisposable()
    private val detailtData = MutableLiveData<DetailMoviesModel>()
    fun getDetailData() : LiveData<DetailMoviesModel> = detailtData


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

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }


}