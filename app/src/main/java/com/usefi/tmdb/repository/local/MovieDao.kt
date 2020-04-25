package com.usefi.tmdb.repository.local

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single


@Dao
public interface MovieDao {

//    @Query("SELECT * FROM movieTable")
//    fun getAllMovies(): Observable<List<LocalEntity>>
//
//    @Query("SELECT * FROM movieTable WHERE movie_id=:movie_id")
//    fun getMovie(movie_id: Int): Single<Boolean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie( movie : LocalEntity) : Completable

//    @Query("DELETE FROM movieTable WHERE movie_id = :movie_id")
//    fun deleteMovie(movie_id: Int) : Completable

}