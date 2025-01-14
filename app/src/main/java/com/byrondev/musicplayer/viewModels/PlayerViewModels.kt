package com.byrondev.musicplayer.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.byrondev.musicplayer.data.models.PlaybackQueue
import com.byrondev.musicplayer.data.models.Song
import com.byrondev.musicplayer.data.repositories.MusicRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PlayerViewModels @Inject constructor(
    private val repository: MusicRepository,

) : ViewModel(){

    private val _currentSong = MutableStateFlow(Song())
    val currentSong : StateFlow<Song> = _currentSong
    private val _currentPosition = MutableStateFlow(0L)
    val currentPosition : StateFlow<Long> = _currentPosition

    private val _currentIndexSong = MutableStateFlow(0)
    val currentIndexSong : StateFlow<Int> = _currentIndexSong

    private val _duration = MutableStateFlow(0L)
    val duration : StateFlow<Long> = _duration


    private val _listSong = MutableStateFlow<List<Song>>(emptyList())
    val listSong : StateFlow<List<Song>> = _listSong

    private var _player: ExoPlayer? = null
    val player = _player


    private val _isPlaying = MutableStateFlow(false)
    val isPlaying  = _isPlaying.asStateFlow()

    private val _currentPlaylist = MutableStateFlow<List<PlaybackQueue>>(emptyList())
    val currentPlaylist: StateFlow<List<PlaybackQueue>> get() = _currentPlaylist


    init {
        viewModelScope.launch {
            repository.getCurrentPlaybackQueue().collect{ items ->
                _currentPlaylist.value = items
            }
        }
    }


    fun playNextItem () {
        println("---currentMediaItem${player?.currentMediaItem}")

        println("---arist${player?.mediaMetadata?.artist}")

        _currentIndexSong.value += 1
        _currentSong.value = _listSong.value[_currentIndexSong.value]
        _player?.seekToNextMediaItem() 

    }

    fun playSeekTo(index : Int) {
        _player?.seekTo(index, 0)
        _currentIndexSong.value = index
        _currentSong.value = listSong.value[index]
        _player?.play()
        _isPlaying.value = true
    }

    fun playSeekToPreviousMediaItem (  ) {
        if(  _currentIndexSong.value < 0) return
        else {
            _currentIndexSong.value -= 1
            _currentSong.value = _listSong.value[_currentIndexSong.value]
            _player?.seekToPreviousMediaItem()
        }
    }

    //Slider position
    //Update currentDuration
    fun updateDuration () {
        if(player?.currentPosition != null){
            _currentPosition.value = player.currentPosition
        }

    }


    fun setPlayer(player: ExoPlayer) {
        this._player = player
        setupPlayerListener()
    }

    fun getTotalDuration () : Long? = _player?.duration?.coerceAtLeast(0)


    private fun setupPlayerListener() {

        _player?.addListener(object : Player.Listener {

            override fun onPlaybackStateChanged(playbackState: Int) {

                _isPlaying.value = playbackState == Player.STATE_READY && player?.isPlaying == true
                if(playbackState == Player.STATE_ENDED){
                    onAudioEnded()
                }

            }

            override fun onIsPlayingChanged(isPlaying: Boolean) {

                super.onIsPlayingChanged(isPlaying)
            }




        })
    }

    fun  onAudioEnded() {
        println(" -----Se acabo" )

    }

    fun loadPlaylist(songs: List<Song>) {
        _listSong.value = songs

        val songsUri = songs.map{ it.uri}

       val uris = songs.map { it -> PlaybackQueue(uri = it.uri) }
        viewModelScope.launch {
            val mediaItems = songsUri.mapNotNull { uri -> runCatching { MediaItem.fromUri(uri) }.getOrNull() }

            repository.insertUriToPlaybackQueue(uris)
            _player?.apply {
                try {
                    stop()
                    clearMediaItems()
                    setMediaItems(mediaItems)
                    prepare()
                } catch (e: Exception) {
                    Log.e("PlayerError", "Error al preparar la lista: ${e.message}")
                }
            }
        }

    }

    fun play() {
        if(listSong.value.isNotEmpty()){
            _currentSong.value = listSong.value[0]
            _player?.play()
            _isPlaying.value = true
        }
        if(player?.currentPosition != null) {
            _currentPosition.value = player.currentPosition
        }
        if(player?.duration != null) {
            _duration.value = player.duration
        }
    }

    fun pause() {
        _player?.pause()
        _isPlaying.value = false
    }

    fun onValueChangeFinished(sliderPosition : Long) {

        player?.seekTo(sliderPosition)

    }



}