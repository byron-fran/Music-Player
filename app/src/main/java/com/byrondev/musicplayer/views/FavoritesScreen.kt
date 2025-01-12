package com.byrondev.musicplayer.views

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.byrondev.musicplayer.components.BottomBar
import com.byrondev.musicplayer.components.songs.SongList
import com.byrondev.musicplayer.components.topbar.CenterTopAppBar
import com.byrondev.musicplayer.viewModels.MusicViewModels
import com.byrondev.musicplayer.viewModels.PlayerViewModels

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun FavoritesScreen(
    navController: NavController,
    musicViewModels: MusicViewModels,
    playerViewModels: PlayerViewModels,

    )   {
    val songsFavorite = musicViewModels.songs.collectAsState().value.filter { it.isFavorite }

   SongList(songsFavorite,false,PaddingValues(0.dp), playerViewModels )
    Scaffold (
        topBar = { CenterTopAppBar("Favorites") {navController.popBackStack()} },
        bottomBar = { BottomBar(navController) }
    ) { paddingValues ->
        SongList(songsFavorite,false,paddingValues, playerViewModels )
    }
}