package com.byrondev.musicplayer.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "albums_favorite")
data class AlbumsFavorites (
    @PrimaryKey
    @ColumnInfo(name="album_id")
    val albumId : Int = 0

)