package com.byrondev.musicplayer.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName =  "playlist")
data class Playlist(

    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,

    @ColumnInfo(name = "name")
    val name : String = ""
)

//PlaylistSongCrossRef table intermedie
@Entity(
    primaryKeys = ["songId","playlistId"],
    tableName = "playlist_song_cross_ref"
)
data class PlaylistSongCrossRef (
    val songId: Int,
    val playlistId: Int,
)