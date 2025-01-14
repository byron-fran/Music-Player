package com.byrondev.musicplayer.data.repositories

import androidx.room.Transaction
import com.byrondev.musicplayer.data.dao.AlbumsDao
import com.byrondev.musicplayer.data.dao.ArtistsDao
import com.byrondev.musicplayer.data.dao.GenresDao
import com.byrondev.musicplayer.data.dao.PlaybackQueueDao
import com.byrondev.musicplayer.data.dao.PlaylistDao
import com.byrondev.musicplayer.data.dao.SongDao
import com.byrondev.musicplayer.data.models.Album
import com.byrondev.musicplayer.data.models.Artist
import com.byrondev.musicplayer.data.models.Genre
import com.byrondev.musicplayer.data.models.PlaybackQueue
import com.byrondev.musicplayer.data.models.Playlist
import com.byrondev.musicplayer.data.models.Song
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MusicRepository @Inject constructor(
    private val albumsDao: AlbumsDao,
    private val songsDao: SongDao,
    private val artistsDao: ArtistsDao,
    private val playbackQueueDao: PlaybackQueueDao,
    private val playlistDao: PlaylistDao,
    private val genresDao: GenresDao
) {
    //Albums repositories
    fun getAllAlbums(): Flow<List<Album>> = albumsDao.getAllAlbums().flowOn(Dispatchers.IO).conflate()

    fun getAlbumWithSongById(id: Int) = albumsDao.getAlbumWithSongs(id)

    // Songs repositories
    fun getSongs() = songsDao.getAllSongs()
    fun getSongsByGenre(genre : String) : Flow<List<Song>> = songsDao.getSongsByGenre(genre)

    suspend fun updateIsFavoriteSong(id : Int , isFavorite : Boolean) = songsDao.updateIsFavoriteSong(id, isFavorite)

    fun getAllArtists(): Flow<List<Artist>> = artistsDao.getAllArtist().flowOn(Dispatchers.IO).conflate()

    fun getArtistWithSongs(id : Int) = artistsDao.getArtistWithSongs(id)

    fun getArtistWithAlbums(id : Int) = artistsDao.getArtistWithAlbums(id)

    // Genres repository
    fun getAllGenres () : Flow<List<Genre>> = genresDao.getAllGenres()

    suspend fun addArtistWithAlbumAndSong(artist: Artist, album: Album, song: Song, genre: Genre) {
        insertArtistAlbumsAndSong(artist, album, song, genre)
    }

    suspend fun insertUriToPlaybackQueue(items : List<PlaybackQueue>) = playbackQueueDao.insertUri(items)

    fun getCurrentPlaybackQueue() = playbackQueueDao.getCurrentPlaybackQueue()

    fun deletePlaybackQueue() = playbackQueueDao.deletePlaybackQueue()

    // Playlist functions
    suspend fun insertPlayList(playlist : Playlist) = playlistDao.insertPlaylist(playlist)

    fun getAllPlaylist() = playlistDao.getAllPlaylists()

    @Transaction
    suspend fun insertArtistAlbumsAndSong(artist: Artist, album: Album, song: Song, genre: Genre) {
        var artistId: Int = 0
        var albumId: Int = 0
        try {
            // Check if artist exist
            val existingArtist = artist.name?.let { artistsDao.getArtist(it.substringBefore(",")) }
            artistId = existingArtist?.id ?: artistsDao.insertArtist(artist).toInt()

            // Check if Album exist
            val existingAlbum = album.title?.let { album.albumArtist?.let { it1 ->
                albumsDao.getAlbumByTitle(it,
                    it1.substringBefore(",")
                )
            } }

            albumId = existingAlbum?.id ?: albumsDao.insertAlbum(album.copy( artistId = artistId,)).toInt()

            //Check if song already exist
            val songExist = song.title?.let { songsDao.getSongByTitle(song.title, song.album ?: "") }

            if (songExist == null) {
                songsDao.insertSong( song.copy(artistId = artistId, albumId = albumId,))
            }
            //Check if genre already exist
            val genreExist =  genresDao.getGenreByName(genre.name)

            if(genreExist == null) {
                genresDao.insertGenre(genre)
            }

        } catch (error: Throwable) {
            println("----Error ${error.message}")
        }
    }
}