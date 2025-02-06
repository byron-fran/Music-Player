package com.byrondev.musicplayer.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RewriteQueriesToDropUnusedColumns
import androidx.room.Transaction
import androidx.room.Update
import com.byrondev.musicplayer.data.databaseViews.AlbumResponse
import com.byrondev.musicplayer.data.models.Album
import com.byrondev.musicplayer.data.relations.AlbumWithSongs
import kotlinx.coroutines.flow.Flow

@Dao
@RewriteQueriesToDropUnusedColumns
interface AlbumsDao {

    @Query("SELECT * FROM AlbumResponse")
    fun getAllAlbums(): Flow<List<AlbumResponse>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlbum(album: Album): Long

    @Query("SELECT * FROM albums a WHERE title=:title COLLATE NOCASE AND artist = :albumArtist LIMIT 1")
    suspend fun getAlbumByTitle(title: String, albumArtist: String): Album?

    @Transaction
    @Query(
        """ 
        SELECT
        a.title,  a.year, a.album_artist as artist, a.id, a.copyright, a.genres, s.uri as cover, a.artist_id,
        a.album_artist, a.release_date, a.tracks_total,a.created_at,
        COUNT(s.album_id)  as num_tracks,
        SUM(s.duration) as duration,
        CASE WHEN af.album_id NOT NULL THEN 1 ELSE 0 END as is_favorite
        FROM albums a
        LEFT JOIN songs s ON s.album_id = a.id
        LEFT JOIN albums_favorite af ON af.album_id = a.id
        WHERE a.id=:id
      """
    )
    fun getAlbumWithSongs(id: Int): Flow<AlbumWithSongs>

    @Update
    suspend fun updateIsFavoriteAlbum(album: Album)
}
