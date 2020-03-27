package com.usefi.tmdb.repository

import com.usefi.tmdb.Retrofit.RetrofitInterface
import com.usefi.tmdb.pojo.DetailMoviesModel
import com.usefi.tmdb.pojo.SearchMoviesModel
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.create

class NetworkRepository(private val retrofit : Retrofit) {

    fun getMovie(query: String, api_key: String) : Observable<SearchMoviesModel> {
        return retrofit.create(RetrofitInterface::class.java).getMovie(query, api_key)
    }

    fun getDetail(itemId : Int, api_key: String) : Observable<DetailMoviesModel>{
        return retrofit.create(RetrofitInterface::class.java).getDetail(itemId, api_key)
    }
}