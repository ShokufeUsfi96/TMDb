package com.usefi.tmdb.repository

import com.usefi.tmdb.pojo.*
import com.usefi.tmdb.utils.RetrofitInterface
import io.reactivex.Observable
import retrofit2.Retrofit

class NetworkRepository(private val retrofit : Retrofit) {

    fun getMovie(query: String, api_key: String) : Observable<SearchMoviesModel> {
        return retrofit.create(RetrofitInterface::class.java).getMovie(query, api_key)
    }

    fun getDetail(itemId : Int, api_key: String) : Observable<DetailMoviesModel>{
        return retrofit.create(RetrofitInterface::class.java).getDetail(itemId, api_key)
    }

    fun getPopulars(api_key: String) : Observable<PopularsModel>{
        return retrofit.create(RetrofitInterface::class.java).getPopulars(api_key)
    }

    fun getNowPLaying(api_key: String) : Observable<NowPlayingModel>{
        return retrofit.create(RetrofitInterface::class.java).getNowPlaying(api_key)
    }

}