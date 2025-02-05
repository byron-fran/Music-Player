package com.byrondev.musicplayer.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "artists", indices = [Index(value = ["name"], unique = true)])
data class Artist(

    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,

    @ColumnInfo(name = "name", collate = ColumnInfo.NOCASE)
    val name : String? = "",

    @ColumnInfo(name="cover")
    val cover : String? = null,

    @ColumnInfo(name = "num_of_albums")
    val numOfAlbums : Int? = 0,

    @ColumnInfo(name = "num_of_songs")
    val numOfSongs : Int? = 0

)
