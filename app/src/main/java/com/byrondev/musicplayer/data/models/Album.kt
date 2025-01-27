package com.byrondev.musicplayer.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "albums", indices = [Index(value = ["title", "album_artist"], unique = true)])
data class Album (
    @PrimaryKey(autoGenerate = true )
    val id : Int  = 0,

    @ColumnInfo(name = "title")
    val title : String = "",

    @ColumnInfo(name = "artist")
    val artist : String = "",

    @ColumnInfo(name = "album_artist")
    val albumArtist : String = "",

    @ColumnInfo(name = "year")
    val year : String = "",

    @ColumnInfo(name = "release_date")
    val releaseDate : String = "",

    @ColumnInfo(name = "genres")
    val genres : String = "",

    @ColumnInfo(name="cover")
    val cover : ByteArray = ByteArray(0),

    @ColumnInfo(name = "copyright")
    val copyright : String = "",

    @ColumnInfo(name="quality")
    val quality : Int = 0,

    @ColumnInfo(name = "duration")
    val duration : Long = 0,

    @ColumnInfo(name = "is_favorite")
    val isFavorite : Boolean = false,

    @ColumnInfo(name = "tracks_total")
    val tracksTotal : Int = 0,

    @ColumnInfo(name = "created_at")
    val createdAt : Long = System.currentTimeMillis(),

    @ColumnInfo(name = "artist_id")
    val artistId : Int = 0 ,


) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Album

        return cover.contentEquals(other.cover)
    }

    override fun hashCode(): Int {
        return cover.contentHashCode()
    }
}
