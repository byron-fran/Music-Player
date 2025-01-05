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
    val cover : ByteArray? = null

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Artist

        if (cover != null) {
            if (other.cover == null) return false
            if (!cover.contentEquals(other.cover)) return false
        } else if (other.cover != null) return false

        return true
    }

    override fun hashCode(): Int {
        return cover?.contentHashCode() ?: 0
    }
}
