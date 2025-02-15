package com.byrondev.musicplayer.data.models

import androidx.compose.ui.graphics.toArgb
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.byrondev.musicplayer.utils.randomColor

@Entity(tableName = "playlist")
data class Playlist(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "name")
    val name: String = "",

    @ColumnInfo(name = "number_of_songs")
    val numberOfSongs : Int = 0,

    @ColumnInfo(name = "duration")
    val duration : Long = 0,

    @ColumnInfo(name = "color1")
    val color1: Int = randomColor().toArgb(),

    @ColumnInfo(name = "color2")
    val color2: Int = randomColor().toArgb(),

    @ColumnInfo(name = "uri1")
    val uri1: String? = null,


)


@Entity(
    primaryKeys = ["songId", "playlistId"],
    tableName = "playlist_song_cross_ref"
)
data class PlaylistSongCrossRef(
    val songId: Int,
    val playlistId: Int,
)