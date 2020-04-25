package com.usefi.tmdb.repository.local

import android.os.Parcelable
import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*


@Entity(tableName = "movieTable")
@Parcelize
public data class LocalEntity(
    @PrimaryKey
    @ColumnInfo(name = "movie_id")
     val movie_id: Int?,

    @ColumnInfo(name = "title")
    val title: String? ,

    @ColumnInfo(name = "overView")
     val overView: String?

) : Parcelable