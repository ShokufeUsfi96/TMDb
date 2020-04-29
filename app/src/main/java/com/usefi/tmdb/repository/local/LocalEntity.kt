package com.usefi.tmdb.repository.local

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Entity(tableName = "movieTable")
@Parcelize
 data class LocalEntity(
    @PrimaryKey
    @ColumnInfo(name = "movie_id")
     val movie_id : Int?,

    @ColumnInfo(name = "title")
    val title : String? ,

    @ColumnInfo(name = "overView")
     val overView : String?,

    @ColumnInfo(name = "rate")
    val rate : String? ,

    @ColumnInfo(name = "date")
    val date : String? ,

    @ColumnInfo(name = "posterPath")
    val posterPath : String,

    @ColumnInfo(name = "backdropPath")
    val backdropPath : String,

    @ColumnInfo(name = "time")
    val time  : String,

    @ColumnInfo(name = "genre")
    val genre : String,

    @ColumnInfo(name = "votes")
    val votes : String

) : Parcelable