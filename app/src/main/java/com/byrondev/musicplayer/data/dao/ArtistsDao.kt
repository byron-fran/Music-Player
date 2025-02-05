package com.byrondev.musicplayer.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.byrondev.musicplayer.data.databaseViews.AlbumResponse
import com.byrondev.musicplayer.data.models.Artist
import com.byrondev.musicplayer.data.relations.ArtistWithSongs
import kotlinx.coroutines.flow.Flow

@Dao
interface ArtistsDao {

    @Query(""" 
        SELECT a.id,a.cover,a.name,
        COUNT(DISTINCT s.id) AS num_of_songs,
        COUNT(DISTINCT al.id) AS num_of_albums
        FROM artists a
        LEFT JOIN albums al ON al.artist_id = a.id
        LEFT JOIN songs s ON s.album_id = al.id
        GROUP BY a.name
      """)
    fun getAllArtist () : Flow<List<Artist>>

    @Query("SELECT * FROM artists WHERE name=:name COLLATE NOCASE")
    suspend  fun getArtist(name : String) : Artist?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArtist(artist: Artist) : Long

    @Transaction
    @Query("SELECT * FROM artists WHERE id = :id ")
    fun getArtistWithSongs(id : Int) : Flow<ArtistWithSongs>

    @Query(""" 
        SELECT
        ar.id, ar.title, ar.artist, ar.year, 
        ar.cover, ar.numOfHiresQuality, 
        ar.artistId,ar.tracksTotal, ar.numTracks
        FROM AlbumResponse ar
        INNER JOIN artists a ON ar.artistId = a.id
        WHERE a.id=:id
        """)
    fun getAlbumsByArtist(id : Int) : Flow<List<AlbumResponse>>
}