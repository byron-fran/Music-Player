package com.byrondev.musicplayer.views.genres

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.byrondev.musicplayer.components.songs.SongList
import com.byrondev.musicplayer.components.topbar.CenterTopAppBar
import com.byrondev.musicplayer.viewModels.MusicViewModels
import com.byrondev.musicplayer.viewModels.PlayerViewModels

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun SongsByGenre(
    navController: NavController,
    musicViewModels: MusicViewModels,
    playerViewModels: PlayerViewModels,
    id: Int,
) {

    val songsByGenre = musicViewModels.songsByGenre.collectAsState()
    val genre = musicViewModels.genres.collectAsState().value.find { it.id == id }

    LaunchedEffect(genre?.name) {
        musicViewModels.getSongsByGenre(genre = genre?.name ?: "")
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            CenterTopAppBar(
                title=genre?.name ?: "",
                onNavigate = { navController.popBackStack() }
            )
            SongList(
                songsByGenre.value,
                showTrackNumber = false,
                playerViewModels,
                navController,
                musicViewModels,
            )
        }
    }
}