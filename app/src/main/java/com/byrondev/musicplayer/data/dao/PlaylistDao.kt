package com.byrondev.musicplayer.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.byrondev.musicplayer.data.models.Playlist
import com.byrondev.musicplayer.data.models.PlaylistSongCrossRef
import com.byrondev.musicplayer.data.relations.PlaylistWithSongs
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaylistDao {

    @Insert
    suspend fun insertPlaylist(playlist: Playlist): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlaylistSongCrossRef(crossRef: PlaylistSongCrossRef)

    @Transaction
    @Query("""
        SELECT * FROM playlist WHERE id = :playlistId
    """)
    fun getPlaylistWithSongs(playlistId: Int): Flow<List<PlaylistWithSongs>>


    @Query(""" 
        SELECT p.id, p.name, 
            COUNT(ps.songId) AS songCount,
            COALESCE(SUM(s.duration), 0) AS duration
        FROM playlist p
        LEFT JOIN playlist_song_cross_ref ps ON p.id = ps.playlistId
        LEFT JOIN songs s ON ps.songId = s.id
        GROUP BY p.id
    """)
    fun getAllPlaylists() :Flow <List<PlaylistWithCountSong>>
}

data class PlaylistWithCountSong(
    val id : Int = 0,
    val name : String = "",
    val songCount : Int = 0,
    val duration : Int = 0
)