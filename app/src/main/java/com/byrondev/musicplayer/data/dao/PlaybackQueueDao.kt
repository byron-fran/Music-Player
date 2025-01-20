package com.byrondev.musicplayer.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.byrondev.musicplayer.data.models.PlaybackQueue
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaybackQueueDao {

    @Query("SELECT * FROM playback_queue")
    fun getCurrentPlaybackQueue() :Flow<List<PlaybackQueue>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUri(items :List< PlaybackQueue>)

    @Query("DELETE FROM playback_queue")
    suspend fun deletePlaybackQueue()

}


