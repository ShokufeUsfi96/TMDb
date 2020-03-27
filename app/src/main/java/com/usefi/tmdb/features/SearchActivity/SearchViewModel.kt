package com.usefi.tmdb.features.SearchActivity

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.usefi.tmdb.base.BaseViewModel
import com.usefi.tmdb.pojo.Result
import com.usefi.tmdb.pojo.SearchMoviesModel
import com.usefi.tmdb.repository.NetworkRepository
import com.usefi.tmdb.repository.Repository
import com.usefi.tmdb.utils.api_key
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SearchViewModel(private val repository : NetworkRepository) : BaseViewModel(){

    private val resultData = MutableLiveData<List<Result>>()
    fun getResultData() : LiveData<List<Result>> = resultData
    private val disposable = CompositeDisposable()

    fun getSearchQuery( query : String) {
        disposable.add(
            repository.getMovie(query, api_key)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                     resultData.value = it.results
                },{
                    Log.d("MYTAG", it.message.toString())
                })
        )
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }
}