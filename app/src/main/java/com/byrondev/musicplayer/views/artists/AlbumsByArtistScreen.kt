package com.byrondev.musicplayer.views.artists

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
import com.byrondev.musicplayer.components.albums.AlbumsList
import com.byrondev.musicplayer.components.topbar.CenterTopAppBar
import com.byrondev.musicplayer.viewModels.MusicViewModels


@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun AlbumsByArtist(navController: NavController, musicViewModels: MusicViewModels, id : Int) {

    val artistWithAlbums  by musicViewModels.artistWithAlbums.collectAsState()
    val albums = artistWithAlbums.sortedByDescending {  it.year.substringBefore("-")?.toInt()  } ?: emptyList()

    LaunchedEffect(id) {
        musicViewModels.getArtistWithAlbums(id)
    }
    Scaffold(
        topBar = { CenterTopAppBar("Albums",  Icons.AutoMirrored.Default.ArrowBack, onNavigate = {navController.popBackStack()}) },
        content = {paddingValues ->
            AlbumsList(albums , navController,paddingValues )
        },
    )
}