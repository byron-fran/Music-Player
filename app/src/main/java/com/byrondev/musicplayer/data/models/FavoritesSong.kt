package com.byrondev.musicplayer.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "favorites_song",
)
data class FavoritesSong (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    val id : Int = 0,

    @ColumnInfo(name="song_id",)
    val songId : Int = 0

)