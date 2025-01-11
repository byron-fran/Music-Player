package com.byrondev.musicplayer.data.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.byrondev.musicplayer.data.models.Artist
import com.byrondev.musicplayer.data.models.Song

data class ArtistWithSongs(
    @Embedded val arist: Artist,
    @Relation(
        parentColumn = "id",
        entityColumn = "artist_id"
    )
    val songs: List<Song>
)
