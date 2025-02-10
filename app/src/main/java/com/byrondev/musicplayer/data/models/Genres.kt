package com.byrondev.musicplayer.data.models

import androidx.compose.ui.graphics.toArgb
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.byrondev.musicplayer.utils.randomColor

@Entity(tableName = "genres")
data class Genre(

    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,

    @ColumnInfo(name = "name")
    val name : String? = "",

    @ColumnInfo(name = "num_of_songs")
    val numOfSongs : Long = 0,

    @ColumnInfo(name = "color1")
    val color1: Int = randomColor().toArgb(),

    @ColumnInfo(name = "color2")
    val color2: Int = randomColor().toArgb(),


    )
