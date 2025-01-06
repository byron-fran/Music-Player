package com.byrondev.musicplayer.views.artists

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.byrondev.musicplayer.components.BottomBar
import com.byrondev.musicplayer.components.albums.AlbumsList
import com.byrondev.musicplayer.components.topbar.CenterTopAppBar
import com.byrondev.musicplayer.viewModels.MusicViewModels


@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun AlbumsByArtist(navController: NavController, musicViewModels: MusicViewModels, id : Int) {

    // Todo  update this
//    val artistWithAlbums  by musicViewModels.artistWithAlbums.collectAsState()
//     val artistName = artistWithAlbums?.artist?.name ?: "Artist Unknown"
//    val albums = artistWithAlbums!!.albums.sortedByDescending {  it.year?.substringBefore("-")?.toInt()  }

    LaunchedEffect(id) {
        musicViewModels.getArtistByIdWithAlbums(id)
    }

    Scaffold(
        topBar = { CenterTopAppBar("Albums",  Icons.AutoMirrored.Default.ArrowBack, onNavigate = {navController.popBackStack()}) },
        content = {paddingValues ->
            AlbumsList(emptyList() /* Todo add List albums real*/ , navController,paddingValues )
        },
        bottomBar = { BottomBar(navController) }
    )

}