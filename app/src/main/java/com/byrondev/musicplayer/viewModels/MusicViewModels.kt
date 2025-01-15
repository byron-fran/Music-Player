package com.byrondev.musicplayer.viewModels

import android.content.Context
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.byrondev.musicplayer.data.dao.SearchResult
import com.byrondev.musicplayer.data.models.Album
import com.byrondev.musicplayer.data.models.Artist
import com.byrondev.musicplayer.data.models.Genre
import com.byrondev.musicplayer.data.models.Playlist
import com.byrondev.musicplayer.data.models.Song
import com.byrondev.musicplayer.data.relations.AlbumWithSongs
import com.byrondev.musicplayer.data.relations.ArtistWithAlbums
import com.byrondev.musicplayer.data.relations.ArtistWithSongs
import com.byrondev.musicplayer.data.repositories.MusicRepository
import com.byrondev.musicplayer.utils.metadata.getAudioMetadata
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.S)
@HiltViewModel
class MusicViewModels @Inject constructor(
    private val repository: MusicRepository,
    @ApplicationContext private val context: Context

) : ViewModel() {

    //state of Modal playlist
    private val _showModalPlaylistActive = MutableStateFlow<Boolean>(false)
    val showModalPlaylistActive : StateFlow<Boolean> get() = _showModalPlaylistActive

    // Estado del sistema
    private val _processingState = MutableStateFlow<ProcessingState>(ProcessingState.Idle)
    val processingState: StateFlow<ProcessingState> = _processingState

    private val _albums = MutableStateFlow<List<Album>>(emptyList())
    val albums = _albums.asStateFlow()

    private val _songs = MutableStateFlow<List<Song>>(emptyList())
    val songs : StateFlow<List<Song>> = _songs

    private val _songsByGenre = MutableStateFlow<List<Song>>(emptyList())
    val songsByGenre : StateFlow<List<Song>> = _songsByGenre

    private val _albumWithSongs = MutableStateFlow<AlbumWithSongs?>(null)
    val albumWithSongs = _albumWithSongs.asStateFlow();

    private val _artists = MutableStateFlow<List<Artist>>(emptyList())
    val artists : StateFlow<List<Artist>> get() = _artists

    // genres states
    private val _genres = MutableStateFlow<List<Genre>>(emptyList())
    val genres : StateFlow<List<Genre>> get() = _genres

    private val _artistWithSongs = MutableStateFlow<ArtistWithSongs?>(null)
    val artistWithSongs : StateFlow<ArtistWithSongs?> get() = _artistWithSongs

    private val _artistWithAlbums = MutableStateFlow<ArtistWithAlbums?>(null)
    val artistWithAlbums : StateFlow<ArtistWithAlbums?> get() = _artistWithAlbums

    private val _searchResultMusic = MutableStateFlow<List<SearchResult>>(emptyList())
    val searchResult : StateFlow<List<SearchResult>> get() = _searchResultMusic
    val job : Job? = null

    fun clearAlbumWithSongs() {
        _albumWithSongs.value = null // Limpia el estado
    }

    private val _playlists = MutableStateFlow<List<Playlist>>(emptyList())
    val playlist : StateFlow<List<Playlist>> get() = _playlists

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllAlbums().collect { item ->

                if (item.isEmpty()) {
                    _albums.value = emptyList()
                } else {
                    _albums.value = item
                }

            }
        }
    }
    init {
        viewModelScope.launch {
            repository.getSongs().collect{
                _songs.value = it
            }
        }
        viewModelScope.launch {
            repository.getAllPlaylist().collect{ items ->
                _playlists.value = items
            }
        }
    }

    private fun addMusicData(artist: Artist, album: Album, song: Song, genre: Genre) {
        viewModelScope.launch {
            repository.addArtistWithAlbumAndSong(artist, album, song, genre)
        }
    }


    fun getAlbumByIdWithSongs(id: Int) = viewModelScope.launch {
        repository.getAlbumWithSongById(id).collect { item ->

            _albumWithSongs.value = item
        }
    }
    // get all songs


    fun getAllArtists () {
        viewModelScope.launch {
            repository.getAllArtists().collect{ items ->
                _artists.value = items
            }
        }
    }
    fun updateIsFavoriteSong(id : Int , isFavorite : Boolean) {
        viewModelScope.launch {
            repository.updateIsFavoriteSong(id, isFavorite)
        }
    }

    // function to get genres
    fun getAllGenres () {
        viewModelScope.launch {
            repository.getAllGenres().collect{ items ->
                _genres.value = items
            }
        }
    }

    fun getSongsByGenre(genre: String) {

        viewModelScope.launch {
            repository.getSongsByGenre(genre).collect{
                _songsByGenre.value = it
            }
        }
    }

    fun getArtistWithSongs (id : Int) {
       viewModelScope.launch {
           repository.getArtistWithSongs(id).collect{items ->
               _artistWithSongs.value = items
           }
       }
    }

    fun getArtistWithAlbums(id : Int) {
        viewModelScope.launch {
            repository.getArtistWithAlbums(id).collect{ items ->
                _artistWithAlbums.value = items
            }
        }
    }

    fun clearArtistWithSongsAndAlbums () {
        _artistWithSongs.value = null
        _artistWithAlbums.value = null
    }

    // clear
    fun clearSearchMusicResult () {
        /* Todo add  this function*/
    }
    fun clearGenres () {
        _songsByGenre.value = emptyList()
    }
    // Playlist function
    fun insertPlaylist(plyalist : Playlist) {
        viewModelScope.launch {
            repository.insertPlayList(plyalist)
        }
    }

    // Search music by query
    fun searchMusicByQuery(query : String) {

        viewModelScope.launch {
            job?.cancel()
            delay(500)
            repository.searchMusicByQuery(query).collect{
                _searchResultMusic.value = it
            }
        }
    }
    fun onChangeValueModal () {
        _showModalPlaylistActive.value = !_showModalPlaylistActive.value
    }
    // Canal para gestionar las tareas
    private val taskChannel = Channel<Uri>(Channel.UNLIMITED)


    init {
        // Iniciar el procesamiento en segundo plano
        processTasks()
    }

    // Agregar una nueva tarea a la cola
    fun addTask(uri: Uri) {
        viewModelScope.launch {
            taskChannel.send(uri)
        }
    }

    // Procesar tareas desde el canal
    @RequiresApi(Build.VERSION_CODES.S)
    private fun processTasks() {
        viewModelScope.launch {
            for (uri in taskChannel) {
                _processingState.value = ProcessingState.Processing(uri)
                try {
                    handleTask(uri) // Procesar la tarea
                } catch (e: Exception) {
                    Log.e("TaskError", "Error processing URI: $uri, ${e.message}")
                } finally {
                    _processingState.value = ProcessingState.Completed(uri)
                }
            }
            _processingState.value = ProcessingState.Idle
        }
    }

    // Lógica para manejar una tarea
    @RequiresApi(Build.VERSION_CODES.S)
    private suspend fun handleTask(uri: Uri) {
        delay(500)
        val retriever = MediaMetadataRetriever()

        try {

            retriever.setDataSource(context, uri)
            val result = getAudioMetadata(context, uri)
            addMusicData(result.artist, result.album, result.song, result.genre)

        } catch (error: Throwable) {
            Log.e("Error db metadata", "${error.message} artBit2")
        } finally {
            retriever.release()
        }
    }
}

sealed class ProcessingState {
    object Idle : ProcessingState()
    data class Processing(val currentTask: Uri) : ProcessingState()
    data class Completed(val lastTask: Uri) : ProcessingState()
}