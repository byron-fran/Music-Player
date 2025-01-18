package com.byrondev.musicplayer.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.byrondev.musicplayer.data.models.Album
import com.byrondev.musicplayer.data.relations.AlbumWithSongs
import kotlinx.coroutines.flow.Flow

@Dao
interface AlbumsDao {

    @Query("""
        SELECT
            a.id as id,a.title as title,a.album_artist as artist,
            a.year as year,a.cover as cover,
        COUNT(CASE WHEN s.bit_rate >= 24 THEN 1 ELSE NULL END) as quality
        FROM albums a
        LEFT JOIN songs s ON s.album_id = a.id
        GROUP BY a.id
    """)
    fun getAllAlbums() : Flow<List<Album>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlbum(album: Album) : Long

    @Query("SELECT * FROM albums WHERE title=:title AND artist = :albumArtist LIMIT 1")
    suspend fun getAlbumByTitle(title : String, albumArtist : String) : Album?

    @Transaction
    @Query("SELECT * FROM albums WHERE id = :id")
    fun getAlbumWithSongs(id: Int): Flow<AlbumWithSongs>
}