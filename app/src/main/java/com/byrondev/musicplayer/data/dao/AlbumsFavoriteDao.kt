package com.byrondev.musicplayer.data.dao

import androidx.room.Dao
import androidx.room.DatabaseView
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.byrondev.musicplayer.data.models.AlbumsFavorites
import kotlinx.coroutines.flow.Flow

@Dao
interface AlbumsFavoriteDao {

    @Query(""" 
        SELECT
        ar.id, ar.title, ar.artist, ar.year, ar.cover, ar.numOfHiresQuality, ar.artistId
        FROM AlbumResponse ar
        INNER JOIN albums_favorite af ON ar.id = af.album_id
        
    """)
    fun getAlbumsFavorite () : Flow<List<AlbumResponse>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlbumFavorite(album : AlbumsFavorites)

    @Delete(entity = AlbumsFavorites::class)
    suspend fun deleteAlbumFavorite(album: AlbumsFavorites)

}

@DatabaseView("""
        SELECT
        a.id,a.title,a.artist, a.year, a.artist_id AS artistId,
        COUNT(CASE WHEN s.audio_bit_depth >= 24 THEN 1 ELSE NULL END) as numOfHiresQuality,
        CASE WHEN s.uri NOT NULL THEN s.uri ELSE "" END as cover
        FROM albums a
        LEFT JOIN songs s ON s.album_id = a.id
        GROUP BY a.id ORDER BY a.year DESC
    """)
data class AlbumResponse (
    val id : Int = 0,
    val title : String = "",
    val artist : String = "",
    val year : String = "",
    val artistId : Int = 0,
    val cover : String = "",
    val numOfHiresQuality : Int = 0,
)
