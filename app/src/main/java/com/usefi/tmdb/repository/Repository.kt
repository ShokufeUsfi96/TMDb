package com.usefi.tmdb.repository

import com.usefi.tmdb.pojo.SearchMoviesModel
import com.usefi.tmdb.repository.local.MovieDao
import com.usefi.tmdb.repository.local.LocalEntity
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class Repository(val network : NetworkRepository, val local : LocalEntity) {

    private lateinit var localDao : MovieDao

        fun getMovie(query: String, api_key: String) : Observable<SearchMoviesModel> {
        return network.getMovie(query, api_key)
    }

//    fun getAllMovies(): Observable<List<LocalEntity>> {
//        return localDao.getAllMovies()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//    }
//    fun checkMovieInDB(MovieID: Int): Single<Boolean> {
//        return localDao.getMovie(MovieID)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//    }
//
//    fun insertMovie(movie: LocalEntity): Completable {
//        return localDao.insertMovie(movie)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//    }
//
//    fun deleteMovie(id: Int): Completable {
//        return localDao.deleteMovie(id)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//    }

}