package com.byrondev.musicplayer.layout

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.media3.exoplayer.ExoPlayer
import androidx.navigation.compose.rememberNavController
import com.byrondev.musicplayer.components.BottomBar
import com.byrondev.musicplayer.components.player.FloatingBarSong
import com.byrondev.musicplayer.navigation.NavManager
import com.byrondev.musicplayer.viewModels.MusicViewModels
import com.byrondev.musicplayer.viewModels.PlayerViewModels
import com.byrondev.musicplayer.views.songs.SongPlayDetail

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun LayoutScreen(
    playerViewModels: PlayerViewModels,
    musicViewModels: MusicViewModels,
    player: ExoPlayer,
) {
    val currentUri = playerViewModels.currentSongUri.collectAsState()
    val showModal = playerViewModels.showModal.collectAsState()
    val navController = rememberNavController()

    Box(
        modifier = Modifier.background(Color.Black).fillMaxSize()
    ) {
        Box (
            modifier = Modifier
                .padding(bottom = if(currentUri.value.trim().isNotEmpty() && !showModal.value) 110.dp else 50.dp )
                .background(Color.Black)
        ){
            NavManager(musicViewModels, player, playerViewModels, navController) {}
        }
        SongPlayDetail(playerViewModels, musicViewModels,navController)

        if(currentUri.value.trim().isNotEmpty() ) {
            FloatingBarSong(playerViewModels, modifier = Modifier.align(Alignment.BottomCenter))
        }
        BottomBar(navController, modifier = Modifier.align(Alignment.BottomCenter))
    }
}