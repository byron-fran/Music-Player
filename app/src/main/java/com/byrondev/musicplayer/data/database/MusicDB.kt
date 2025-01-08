package com.byrondev.musicplayer.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.byrondev.musicplayer.data.dao.AlbumsDao
import com.byrondev.musicplayer.data.dao.ArtistsDao
import com.byrondev.musicplayer.data.dao.PlaybackQueueDao
import com.byrondev.musicplayer.data.dao.SongDao
import com.byrondev.musicplayer.data.models.Album
import com.byrondev.musicplayer.data.models.Artist
import com.byrondev.musicplayer.data.models.PlaybackQueue
import com.byrondev.musicplayer.data.models.Song

@Database(entities = [Album::class, Artist::class, Song::class, PlaybackQueue::class], version = 47)
abstract class MusicDB : RoomDatabase() {
    abstract fun albumDao () : AlbumsDao
    abstract fun songDao () : SongDao
    abstract fun artistDao () : ArtistsDao
    abstract fun playbackQueueDao () : PlaybackQueueDao

}