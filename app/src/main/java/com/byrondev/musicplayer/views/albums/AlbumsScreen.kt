package com.byrondev.musicplayer.views.albums

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.byrondev.musicplayer.components.albums.AlbumsList
import com.byrondev.musicplayer.viewModels.MusicViewModels

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun AlbumsScreen(navController : NavController, musicViewModels: MusicViewModels,) {

    val listAlbums = musicViewModels.albums.collectAsState().value

    AlbumsList(listAlbums, navController, PaddingValues(0.dp))

}
