package com.byrondev.musicplayer.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.byrondev.musicplayer.data.models.Song
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerViewModels @Inject constructor() : ViewModel(){

    private val _currentSong = MutableStateFlow(Song())
    val currentSong : StateFlow<Song> = _currentSong
    private val _currentPosition = MutableStateFlow(0L)
    val currentPosition : StateFlow<Long> = _currentPosition

    private val _indexSong = MutableStateFlow(0)
    val indexSong : StateFlow<Int> = _indexSong


    private val _duration = MutableStateFlow(0L)
    val duration : StateFlow<Long> = _duration


    private val _listSong = MutableStateFlow<List<Song>>(emptyList())
    val listSong : StateFlow<List<Song>> = _listSong

    private var _player: ExoPlayer? = null
    val player = _player


    private val _isPlaying = MutableStateFlow(false)
    val isPlaying  = _isPlaying.asStateFlow()

    private val _currentPlaylist = MutableStateFlow<List<MediaItem>>(emptyList())
    val currentPlaylist: StateFlow<List<MediaItem>> get() = _currentPlaylist

    fun playByMediaItem(song: Song, index: Int) {
        println("----------index $index")
            println("-------player now ${player.toString()}")
//        _player?.setMediaItem(MediaItem.fromUri(song.uri))
//        _player?.play()
        _player?.seekTo(index, 0)
        _player?.play()
        _isPlaying.value = true
        _currentSong.value = song

    }

    fun playNextItem () {
        println("---currentMediaItem${player?.currentMediaItem}")

        println("---arist${player?.mediaMetadata?.artist}")

        _indexSong.value += 1
        _currentSong.value = _listSong.value[indexSong.value]
        _player?.seekToNextMediaItem() 

    }

    fun playSeekToPreviousMediaItem (  ) {
        if(indexSong.value < 0) return
        else {
            _indexSong.value -= 1
            _currentSong.value = _listSong.value[indexSong.value]
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

        viewModelScope
            .launch {
            val mediaItems = songsUri.mapNotNull { uri ->
                runCatching { MediaItem.fromUri(uri) }.getOrNull()
            }
            println("---------mediaItems $mediaItems")
            if (_currentPlaylist.value != mediaItems) {
                _currentPlaylist.value = mediaItems
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
    }

    fun play() {
        _currentSong.value = listSong.value[0]
        _player?.play()
        _isPlaying.value = true
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