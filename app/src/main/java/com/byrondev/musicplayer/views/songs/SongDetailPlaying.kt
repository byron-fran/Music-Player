package com.byrondev.musicplayer.views.songs


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.byrondev.musicplayer.components.images.BackgroundImage
import com.byrondev.musicplayer.components.images.CoverImage
import com.byrondev.musicplayer.components.songs.SongBitDepth
import com.byrondev.musicplayer.components.songs.SongController
import com.byrondev.musicplayer.components.songs.SongIconsEvents
import com.byrondev.musicplayer.components.songs.SongPlayInfo
import com.byrondev.musicplayer.components.songs.SongSlider
import com.byrondev.musicplayer.components.songs.SongTopOptions
import com.byrondev.musicplayer.viewModels.MusicViewModels
import com.byrondev.musicplayer.viewModels.PlayerViewModels


@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun SongPlayDetail(
    showModal: MutableState<Boolean>,
    playerViewModels: PlayerViewModels
    ) {

    val song = playerViewModels.currentSong.collectAsState()


    AnimatedVisibility (
        visible = showModal.value,
        enter = slideInVertically (initialOffsetY = { it }) + fadeIn(),
        exit = slideOutVertically (targetOffsetY = { it }) + fadeOut()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .clickable(interactionSource = remember { MutableInteractionSource() }, indication = null) {}
        ) {
            Box(
                modifier = Modifier.fillMaxSize().background(Color.Black)
            ) {

//                BackgroundImage(song.value.cover, modifier = Modifier.fillMaxSize())
                Column (modifier = Modifier.padding(horizontal =  25.dp))  {
                    SongTopOptions(song.value, showModal)
                    SongBitDepth(song.value)
                    CoverImage(song.value.cover, modifier = Modifier.height(370.dp))
                    SongPlayInfo(song.value)
                    SongIconsEvents(song.value, playerViewModels)
                    SongSlider(song.value, playerViewModels)
                    SongController(playerViewModels)
                }
            }
        }
    }
}