package com.byrondev.musicplayer.views

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.byrondev.musicplayer.components.albums.AlbumsCarousel
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
    val albumsFavorite = musicViewModels.albumsFavorites.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            CenterTopAppBar(title = "Favorites") { }
            AlbumsCarousel(
                albumsFavorite.value,
                navController,
                text1 = "Albumes Favoritos",
                text2 = "",
            ) { }
        SongList(songsFavorite,false, PaddingValues(0.dp), playerViewModels, navController )
       }
    }
}