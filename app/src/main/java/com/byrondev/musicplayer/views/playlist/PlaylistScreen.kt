package com.byrondev.musicplayer.views.playlist

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.byrondev.musicplayer.components.playlist.PlaylistItem
import com.byrondev.musicplayer.viewModels.MusicViewModels


@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun PlaylistScreen(musicViewModels: MusicViewModels, navController: NavController) {

    val playlists = musicViewModels.playlist.collectAsState()

    Column (modifier = Modifier.background(color = Color.Black).fillMaxSize()){
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.offset(x = 0.dp, y = 10.dp)
        ) {
            items(playlists.value) {
                PlaylistItem(it)
            }
        }
    }
}
