package com.byrondev.musicplayer.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(tableName = "songs", indices = [Index(value = [ "album_id", "artist_id"])])
data class Song(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "title")
    val title: String? = "",

    @ColumnInfo(name = "artist")
    val artist: String? = "unknown",

    @ColumnInfo(name = "duration")
    val duration: Long = 0,

    @ColumnInfo(name = "track_number")
    val trackNumber: Int? = 0,

    @ColumnInfo(name = "year")
    val year: String? = "",

    @ColumnInfo(name = "disk")
    val disk: Int? = 1,

    @ColumnInfo(name = "cover")
    val cover: ByteArray? = null,

    @ColumnInfo(name = "bits_per_sample")
    val bitsPerSample: Int? = 0,

    @ColumnInfo(name = "sample_rate")
    val sampleRate: Int? = 0,

    @ColumnInfo(name = "bit_rate")
    val bitRate: Int? = 0,

    @ColumnInfo(name = "audio_codec")
    val audioCodec: String? = "",

    @ColumnInfo(name = "numbers_of_channels")
    val numberOfChannels: Int = 0,

    @ColumnInfo(name = "album")
    val album: String? = "",

    @ColumnInfo(name="genre")
    val genre: String? = "",

    @ColumnInfo(name = "is_favorite")
    val isFavorite : Boolean = false,

    @ColumnInfo(name = "uri")
    val uri: String = "",

    @ColumnInfo(name = "listeners")
    val listeners: Long = 0,

    @ColumnInfo(name = "composer")
    val composer: String? = "",

    @ColumnInfo(name = "copyright")
    val copyright: String? = "",


    @ColumnInfo(name = "album_id")
    val albumId: Int? = 0,

    @ColumnInfo(name = "artist_id")
    val artistId: Int? = 0,

    )
