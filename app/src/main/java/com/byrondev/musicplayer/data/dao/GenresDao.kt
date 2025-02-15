package com.byrondev.musicplayer.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.byrondev.musicplayer.data.models.Genre
import kotlinx.coroutines.flow.Flow

@Dao
interface GenresDao {

    @Query("""
        SELECT g.id, g.name, g.color1, g.color2,
        COUNT(s.id) as num_of_songs
        FROM genres g
        LEFT JOIN songs s ON s.genre = g.name
        GROUP BY g.name ORDER BY g.name DESC
    """)
    fun getAllGenres () : Flow<List<Genre>>

    @Query("SELECT * FROM genres WHERE name = :name COLLATE NOCASE LIMIT 1")
    suspend fun getGenreByName(name : String) : Genre?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insertGenre(genre: Genre)
}