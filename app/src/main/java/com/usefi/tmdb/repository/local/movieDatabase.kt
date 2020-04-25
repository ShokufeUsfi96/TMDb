package com.usefi.tmdb.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [LocalEntity::class], version = 1)
public abstract class movieDatabase : RoomDatabase() {

    public abstract fun getMovieDao() : MovieDao

    companion object{
    fun getDbInstance(context : Context) : movieDatabase? {
        var movieDB : movieDatabase? = null
        if (movieDB == null) {
            synchronized(this) {
                movieDB = Room.databaseBuilder(
                    context.applicationContext,
                    movieDatabase::class.java, "movieDB.db"
                )
                    .build()
            }
        }
        fun destroyInstance() {
            movieDB = null
        }
        return movieDB

    }

    }

}