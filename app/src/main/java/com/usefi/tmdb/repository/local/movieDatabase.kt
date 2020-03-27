package com.usefi.tmdb.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [LocalEntity::class], version = 1)
public abstract class movieDatabase : RoomDatabase() {

     private  lateinit var DaoInterface : DaoInterface

    private var movieDB : movieDatabase? = null

    fun getDatabase(context : Context) : movieDatabase?{
        if (movieDB == null) {
            synchronized(movieDatabase::class) {
                movieDB = Room.databaseBuilder(context.getApplicationContext(),
                        movieDatabase::class.java, "movieDB.db")
                    .build()
            }
        }
        fun destroyInstance() {
            movieDB = null
        }
        return movieDB

    }

}