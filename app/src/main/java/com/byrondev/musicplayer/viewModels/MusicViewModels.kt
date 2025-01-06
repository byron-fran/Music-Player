package com.byrondev.musicplayer.viewModels

import android.content.Context
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.byrondev.musicplayer.data.models.Album
import com.byrondev.musicplayer.data.models.Artist
import com.byrondev.musicplayer.data.models.Song
import com.byrondev.musicplayer.data.relations.AlbumWithSongs
import com.byrondev.musicplayer.data.repositories.MusicRepository
import com.byrondev.musicplayer.utils.metadata.getAudioMetadata
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
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

    // Estado del sistema
    private val _processingState = MutableStateFlow<ProcessingState>(ProcessingState.Idle)
    val processingState: StateFlow<ProcessingState> = _processingState

    private val _albums = MutableStateFlow<List<Album>>(emptyList())
    val albums = _albums.asStateFlow()

    private val _songDetail = MutableStateFlow(Song())
    val songDetail = _songDetail.asStateFlow()

    private val _albumWithSongs = MutableStateFlow<AlbumWithSongs?>(null)
    val albumWithSongs = _albumWithSongs.asStateFlow();

    fun clearAlbumWithSongs() {
        _albumWithSongs.value = null // Limpia el estado
    }

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

    private fun addMusicData(artist: Artist, album: Album, song: Song) {
        viewModelScope.launch {
            repository.addArtistWithAlbumAndSong(artist, album, song)
        }
    }


    fun getAlbumByIdWithSongs(id: Int) = viewModelScope.launch {
        repository.getAlbumWithSongById(id).collect { item ->

            _albumWithSongs.value = item
        }
    }

    fun getArtistByIdWithSong (id : Int) {
        /*Todo Add this function*/
    }
    fun getArtistByIdWithAlbums (id : Int) {
        /* Todo Add this function*/
    }
    fun clearArtistWithSongs () {
        /* Todo Add this function*/
    }

    // clear
    fun clearSearchMusicResult () {
        /* Todo add  this function*/
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
        delay(10)
        Log.d("TaskProcessing", "Processing URI: $uri")
        val retriever = MediaMetadataRetriever()

        try {

            retriever.setDataSource(context, uri)
            val result = getAudioMetadata(context, uri)
            addMusicData(result.artist, result.album, result.song)

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






