package com.byrondev.musicplayer.data.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.byrondev.musicplayer.data.models.Playlist
import com.byrondev.musicplayer.data.models.PlaylistSongCrossRef
import com.byrondev.musicplayer.data.models.Song

data class PlaylistWithSongs(
    @Embedded val playlist: Playlist,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            PlaylistSongCrossRef::class,
            entityColumn = "songId",
            parentColumn = "playlistId"

        )
    )
    val songs: List<Song>,
)

