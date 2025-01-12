package com.byrondev.musicplayer.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.byrondev.musicplayer.data.models.Genre
import kotlinx.coroutines.flow.Flow

@Dao
interface GenresDao {

    @Query("SELECT * FROM genres")
    fun getAllGenres () : Flow<List<Genre>>

    @Query("SELECT * FROM genres WHERE name = :name COLLATE NOCASE LIMIT 1")
    suspend fun getGenreByName(name : String) : Genre?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insertGenre(genre: Genre)
}