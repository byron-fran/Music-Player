package com.byrondev.musicplayer.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.byrondev.musicplayer.data.models.Playlist
import com.byrondev.musicplayer.data.models.PlaylistSongCrossRef
import com.byrondev.musicplayer.data.models.Song
import com.byrondev.musicplayer.data.relations.PlaylistWithSongs
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaylistDao {

    @Insert
    suspend fun insertPlaylist(playlist: Playlist): Long

    @Insert
    suspend fun insertSong(song: Song): Long

    @Insert
    suspend fun insertPlaylistSongCrossRef(crossRef: PlaylistSongCrossRef)

    @Transaction
    @Query("SELECT * FROM playlist WHERE id = :playlistId")
    suspend fun getPlaylistWithSongs(playlistId: Int): PlaylistWithSongs

    @Query("SELECT * FROM playlist")
    fun getAllPlaylists() :Flow <List<Playlist>>
}