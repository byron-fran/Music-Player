package com.byrondev.musicplayer.views.artists

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.byrondev.musicplayer.R
import com.byrondev.musicplayer.components.albums.AlbumsList
import com.byrondev.musicplayer.components.topbar.CenterTopAppBar
import com.byrondev.musicplayer.viewModels.MusicViewModels


@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun AlbumsByArtist(
    navController: NavController,
    musicViewModels: MusicViewModels,
    id: Int,
) {

    val artistWithAlbums by musicViewModels.artistWithAlbums.collectAsState()
    val albums = artistWithAlbums.sortedByDescending { it.year.substringBefore("-").toInt() }

    LaunchedEffect(id) {
        musicViewModels.getArtistWithAlbums(id)
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            CenterTopAppBar(
                stringResource(id = R.string.library_albums),
                onNavigate = { navController.popBackStack() }
            )
            AlbumsList(albums, navController)
        }
    }
}