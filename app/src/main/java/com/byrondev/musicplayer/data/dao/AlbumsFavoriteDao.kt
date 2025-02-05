package com.byrondev.musicplayer.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.byrondev.musicplayer.data.databaseViews.AlbumResponse
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