package com.byrondev.musicplayer.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.byrondev.musicplayer.data.models.FavoritesSong
import com.byrondev.musicplayer.data.models.Song
import kotlinx.coroutines.flow.Flow


@Dao
interface FavoritesSongDao {

    @Query("""
        SELECT 
        fs.id as song_id, fs.song_id as id,s.duration,   
        s.uri,s.title, s.artist, s.album, 
        s.artist_id, s.album_id, s.audio_bit_depth, s.track_number, s.disk,
        s.listeners, 
        SUBSTR(s.year, 1, INSTR(s.year, "-") -1)  as year,
        CASE WHEN  fs.id NOT NULL THEN 1 ELSE 0 END as is_favorite
        FROM songs s
        INNER JOIN favorites_song fs ON  s.id = fs.song_id 
        GROUP BY   fs.id ORDER BY fs.id DESC
    """)
    fun getFavoritesSong () : Flow<List<Song>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertFavoriteSong(songs : List<FavoritesSong>)

    @Query("DELETE FROM favorites_song  WHERE song_id=:id")
    suspend fun deleteFavoriteSong(id : Int)

}