package com.byrondev.musicplayer.views.songs

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.byrondev.musicplayer.components.BottomBar
import com.byrondev.musicplayer.components.songs.SongList
import com.byrondev.musicplayer.components.topbar.CenterTopAppBar
import com.byrondev.musicplayer.viewModels.MusicViewModels
import com.byrondev.musicplayer.viewModels.PlayerViewModels

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun SongsByArtist(navController: NavController, musicViewModels: MusicViewModels, playerViewModels : PlayerViewModels, id : Int, ) {

    val artistWithSongs  by musicViewModels.artistWithSongs.collectAsState()
    val songs = artistWithSongs?.songs?.sortedByDescending {  it.year?.substringBefore("-")?.toInt()  } ?: emptyList()

    LaunchedEffect(id) {

        musicViewModels.getArtistWithSongs(id)
    }

    Scaffold(
        topBar = { CenterTopAppBar("Songs", Icons.AutoMirrored.Default.ArrowBack, onNavigate = { navController.popBackStack()}) },
        content = {paddingValues ->
                SongList(songs, showTrackNumber = false, paddingValues, playerViewModels, navController)
        },
        bottomBar = { BottomBar(navController) }
    )
}