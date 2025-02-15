package com.byrondev.musicplayer.views.playlist

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.byrondev.musicplayer.R
import com.byrondev.musicplayer.components.playlist.PlaylistItems
import com.byrondev.musicplayer.components.topbar.CenterTopAppBar
import com.byrondev.musicplayer.viewModels.MusicViewModels


@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun PlaylistScreen(
    musicViewModels: MusicViewModels,
    navController: NavController,
    songId: Int = 0,
) {

    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            CenterTopAppBar(
                title = stringResource(R.string.library_playlists),
                iconColor = Color.White
            ) {
                navController.popBackStack()
            }
            PlaylistItems(
                musicViewModels,
                navController,
                songId = songId
            )

        }
    }
}