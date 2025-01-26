package com.byrondev.musicplayer.views.playlist

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.byrondev.musicplayer.components.playlist.PlaylistItems
import com.byrondev.musicplayer.components.topbar.CenterTopAppBar
import com.byrondev.musicplayer.viewModels.MusicViewModels


@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun PlaylistScreen(musicViewModels: MusicViewModels, navController: NavController, songId : Int = 0) {

    Scaffold (
        topBar = { CenterTopAppBar("Playlists"){navController.popBackStack()} },
        containerColor = Color.Black
    ) { paddingValues ->
        PlaylistItems(musicViewModels, navController, paddingValues, songId)
    }
}
