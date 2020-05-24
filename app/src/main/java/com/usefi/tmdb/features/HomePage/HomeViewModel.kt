package com.usefi.tmdb.features.HomePage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.usefi.tmdb.base.BaseViewModel
import com.usefi.tmdb.pojo.PopularsModel
import com.usefi.tmdb.pojo.Result
import com.usefi.tmdb.repository.NetworkRepository
import com.usefi.tmdb.utils.api_key
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomeViewModel(private val repository : NetworkRepository) : BaseViewModel() {
    private val popularsRes = MutableLiveData<List<Result>>()
    fun getPopularsRes(): LiveData<List<Result>> = popularsRes

    private val playingRes = MutableLiveData<List<Result>>()
    fun getPlayingRes(): LiveData<List<Result>> = playingRes

    private val disposable = CompositeDisposable()

    fun getPopularsData() {
        disposable.add(
            repository.getPopulars(api_key)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    popularsRes.value = it.results
                },{
                    Log.d("MYTAG",it.message.toString())
                })
        )
    }

    fun getNowPlayingData() {
        disposable.add(
            repository.getNowPLaying(api_key)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    playingRes.value = it.results
                }, {
                    Log.d("MYTAG", it.message.toString())
                })
        )
    }

        override fun onCleared() {
            disposable.clear()
            super.onCleared()
        }
    }

