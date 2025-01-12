package com.byrondev.musicplayer.data.dao

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

data class SearchResult(
    val name: String,
    val artist : String?,
    val album : String?,
    val uri : String?,
    val id: Int,
    val cover: ByteArray?,
    val type: String,
    val bitrate : Int

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SearchResult

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

@Dao
interface MusicDao {

    @Query("""
            SELECT title AS name, artist,cover, album, uri, bit_rate,  id, 'Song' AS type
            FROM songs
            WHERE LOWER(title) LIKE LOWER('%' || :query || '%')
            UNION
            SELECT albums.title AS name, albums.album_artist as artist, cover, "" as album, "" as uri, "" as audio_bit_depth, id,  'Album' AS type
            FROM albums
            WHERE LOWER(albums.title) LIKE  LOWER('%' || :query || '%') 
            UNION
            SELECT artists.name AS name, "" as artist, cover, "" as album, "" as uri, "" as audio_bit_depth, artists.id AS id, 'Artist' AS type
            FROM artists
            WHERE  LOWER(artists.name) LIKE  LOWER('%' || :query || '%'); 
            
            """)
    fun searchSong(query : String) : Flow<List<SearchResult>>
}