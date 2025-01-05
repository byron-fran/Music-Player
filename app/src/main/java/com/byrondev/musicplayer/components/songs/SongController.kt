package com.byrondev.musicplayer.components.songs

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.byrondev.musicplayer.R
import com.byrondev.musicplayer.viewModels.PlayerViewModels

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun SongController(playerViewModels : PlayerViewModels) {

    val isPlaying = playerViewModels.isPlaying.collectAsState()
    val indexSong = playerViewModels.indexSong.collectAsState()
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.arrow_start),
            contentDescription = "",
            tint = Color.White,
            modifier = Modifier.size(30.dp).clickable {
                if(indexSong.value <= 0) return@clickable
                else {playerViewModels.playSeekToPreviousMediaItem() }
            }
        )

        if(isPlaying.value) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_stop),
                contentDescription = "Pause audio",
                tint = Color.White,
                modifier = Modifier.size(30.dp).clickable { playerViewModels.pause() }
            )
        }
        else {
            Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = "Play audio",
                tint = Color.White,
                modifier = Modifier.size(30.dp).clickable { playerViewModels.play() }

            )
        }

        Icon(
            painter = painterResource(id = R.drawable.arrow_end),
            contentDescription = "",
            tint = Color.White,
            modifier = Modifier.size(30.dp).clickable { playerViewModels.playNextItem() }
        )
    }

}