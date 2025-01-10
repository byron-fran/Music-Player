package com.byrondev.musicplayer.components.player

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.byrondev.musicplayer.R
import com.byrondev.musicplayer.components.images.CoverImage
import com.byrondev.musicplayer.viewModels.PlayerViewModels

@Composable
fun FloatingBarSong(
    showModal : MutableState<Boolean>,
    playerViewModels: PlayerViewModels,
    modifier: Modifier = Modifier

    ) {
    val currentSong = playerViewModels.currentSong.collectAsState()
    FloatingActionButton(
        onClick =  { showModal.value = true  },
        modifier = modifier.fillMaxWidth().padding(bottom =65.dp),
        containerColor =  Color.DarkGray,
        shape = RoundedCornerShape(5.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Row {
                CoverImage(currentSong.value.cover, modifier = Modifier.height(50.dp).width(50.dp))
                Column {
                    Text("${currentSong.value.title}", color= Color.White)
                    Text("${currentSong.value.album}", color= Color.White)

                }
            }

            Icon(
                painter =  if(playerViewModels.isPlaying.collectAsState().value) {
                    painterResource(id= R.drawable.baseline_stop)
                } else {
                    painterResource(id= R.drawable.baseline_play_arrow_30)
                },
                modifier = Modifier.size(30.dp).clickable {
                    if(playerViewModels.isPlaying.value) {
                        playerViewModels.pause()
                    }
                    else {
                        playerViewModels.play()
                    }
                },
                tint = Color.White,
                contentDescription = "icon to Play"
            )

        }
    }
}