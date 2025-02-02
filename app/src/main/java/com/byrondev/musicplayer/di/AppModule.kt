package com.byrondev.musicplayer.di

import android.content.Context
import androidx.room.Room
import com.byrondev.musicplayer.data.dao.AlbumsDao
import com.byrondev.musicplayer.data.dao.AlbumsFavoriteDao
import com.byrondev.musicplayer.data.dao.ArtistsDao
import com.byrondev.musicplayer.data.dao.GenresDao
import com.byrondev.musicplayer.data.dao.PlaybackQueueDao
import com.byrondev.musicplayer.data.dao.PlaylistDao
import com.byrondev.musicplayer.data.dao.SearchDao
import com.byrondev.musicplayer.data.dao.SongDao
import com.byrondev.musicplayer.data.database.MusicDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providerDatabase(@ApplicationContext context: Context): MusicDB {
        return Room.databaseBuilder(
            context,
            MusicDB::class.java,
            name = "music_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideAlbumsDao(database: MusicDB): AlbumsDao = database.albumDao()

    @Singleton
    @Provides
    fun provideSongsDao(database: MusicDB): SongDao = database.songDao()

    @Singleton
    @Provides
    fun providerArtistDao(database: MusicDB) : ArtistsDao = database.artistDao()

    @Singleton
    @Provides
    fun providesPlaybackQueueDao(database: MusicDB) : PlaybackQueueDao = database.playbackQueueDao()

    @Singleton
    @Provides
    fun providesPlaylist(database: MusicDB) : PlaylistDao = database.playlistDao()

    @Singleton
    @Provides
    fun providesGenres(database: MusicDB) : GenresDao = database.genresDao()

    @Singleton
    @Provides
    fun provideSearchMusic(database: MusicDB) : SearchDao = database.searchDao()

    @Singleton
    @Provides
    fun provideAlbumsFavorite(database: MusicDB) : AlbumsFavoriteDao = database.albumsFavoriteDao()
}