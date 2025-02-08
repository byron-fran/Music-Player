package com.byrondev.musicplayer.views.songs

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import com.byrondev.musicplayer.components.songs.SongList
import com.byrondev.musicplayer.viewModels.MusicViewModels
import com.byrondev.musicplayer.viewModels.PlayerViewModels

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun SongsScreen(
    musicViewModels: MusicViewModels,
    navController: NavController,
    playerViewModels: PlayerViewModels,
) {
    val listSong = musicViewModels.songs.collectAsState()

    SongList(
        listSong.value,
        showTrackNumber = false,
        playerViewModels, navController,
        musicViewModels
    )

}
