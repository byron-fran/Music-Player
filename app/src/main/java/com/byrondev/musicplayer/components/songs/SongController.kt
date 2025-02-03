package com.byrondev.musicplayer.components.songs

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import com.byrondev.musicplayer.R
import com.byrondev.musicplayer.viewModels.PlayerViewModels
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun SongController(playerViewModels : PlayerViewModels) {

    val isPlaying = playerViewModels.isPlaying.collectAsState()
    val currentIndexSong= playerViewModels.currentIndexSong.collectAsState()
    val currentPlaylist = playerViewModels.currentPlaylist.collectAsState()

    Row(
        modifier = Modifier.fillMaxWidth().offset(x=0.dp, y=5.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        
        Icon(
            painter = painterResource(id = R.drawable.arrow_start),
            contentDescription = "",
            tint = Color.White,
            modifier = Modifier.size(30.dp).clickable {
                if(currentIndexSong.value <= 0) return@clickable
                else {
                    playerViewModels.viewModelScope.launch {
                        playerViewModels.playSeekToPreviousMediaItem()
                    }
                }
            }
        )

            Icon(
                painter = if (isPlaying.value) painterResource(id=R.drawable.baseline_stop_30) else painterResource(id=R.drawable.baseline_play_30),
                contentDescription = "Play audio",
                tint = Color.White,
                modifier = Modifier.size(50.dp).clickable {
                    if (isPlaying.value) playerViewModels.pause()
                    else playerViewModels.viewModelScope.launch {
                        playerViewModels.playContinue()
                    }
                }
            )

        Icon(
            painter = painterResource(id = R.drawable.arrow_end),
            contentDescription = "",
            tint = Color.White,
            modifier = Modifier.size(30.dp).clickable {
                if(currentIndexSong.value >= currentPlaylist.value.size) return@clickable
                else  {
                    playerViewModels.viewModelScope.launch {
                        playerViewModels.playNextItem()
                    }
                }
            }
        )
    }
}