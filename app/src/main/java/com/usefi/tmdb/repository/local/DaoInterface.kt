package com.usefi.tmdb.repository.local

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single


@Dao
public interface DaoInterface {

    @Query("Select * from movieTable")
    fun getBookmarks(): Observable<List<LocalEntity>>

    @Query("select COUNT(*)>0 from movieTable where movie_id=:movie_id")
    fun getMovie(movie_id: Int): Single<Boolean>

    @Insert
    fun insertMovie( movie: LocalEntity) : Completable

    @Delete
    fun deleteMovie(id : Int) : Completable

}