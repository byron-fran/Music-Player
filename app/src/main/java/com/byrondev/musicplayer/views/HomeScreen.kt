package com.byrondev.musicplayer.views

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.byrondev.musicplayer.R
import com.byrondev.musicplayer.components.albums.AlbumsCarousel
import com.byrondev.musicplayer.components.files.SelectedLaunchedFiles
import com.byrondev.musicplayer.components.home.CardHome
import com.byrondev.musicplayer.components.playlist.PlaylistCarousel
import com.byrondev.musicplayer.components.playlist.PlaylistModal
import com.byrondev.musicplayer.components.topbar.CenterTopAppBar
import com.byrondev.musicplayer.viewModels.MusicViewModels

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun HomeScreen(navController: NavController, musicViewModels: MusicViewModels) {

    val songs = musicViewModels.songs.collectAsState()
    val playlistItems = musicViewModels.playlist.collectAsState()
    val albums = musicViewModels.albums.collectAsState().value.sortedByDescending { it.id }
    val albumsFavorites = musicViewModels.albumsFavorites.collectAsState()

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black), ) {
        Column {
            CenterTopAppBar ("Home", Icons.Default.Settings){ navController.navigate("SettingsScreen") }
            LazyColumn(modifier = Modifier.fillMaxSize())
            {
                items(1) {
                    if(songs.value.isEmpty()) {
                        SelectedLaunchedFiles(musicViewModels) {
                            CardHome(onClick = { it.launch(null) }, "Agregar música", painterResource(id = R.drawable.baseline_add_30))
                        }
                    }
                    if(albums.isNotEmpty()) {
                        AlbumsCarousel(albums.take(10), navController, text1 = "Agrego recientemente", text2 = "") { }
                    }
                    if(playlistItems.value.isNotEmpty()) {
                        PlaylistCarousel(playlistItems.value.take(5), navController)

                    }
                    else {
                        CardHome(onClick = { musicViewModels.onChangeValueModal()}, "Agregar nueva lista de reproduccion", painterResource(id=R.drawable.baseline_add_30))
                        PlaylistModal(musicViewModels)
                    }
                    if(albumsFavorites.value.isNotEmpty()) {
                        AlbumsCarousel(albumsFavorites.value.take(10), navController, text1 = "Albumes favoritos", text2 = "ver más") {navController.navigate("FavoritesScreen") }
                    }
                    else {
                        CardHome(onClick = { navController.navigate("FavoritesScreen") }, "Favoritos", painterResource(id=R.drawable.favorite_filled))
                    }

                    CardHome(onClick = { /* Todo add event*/ }, "Historial de reproducciones", painterResource(id=R.drawable.baseline_cd_30))
                }
            }
        }
    }
}