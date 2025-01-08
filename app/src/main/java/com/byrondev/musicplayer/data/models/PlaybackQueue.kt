package com.byrondev.musicplayer.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID


@Entity(tableName = "playback_queue")
data class PlaybackQueue  (

    @ColumnInfo(name = "id", index = true)
    @PrimaryKey
    val id : String = UUID.randomUUID().toString(),

    @ColumnInfo(name="uri")
    val uri : String = ""

)