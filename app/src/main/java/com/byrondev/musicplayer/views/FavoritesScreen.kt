package com.byrondev.musicplayer.views

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.byrondev.musicplayer.R
import com.byrondev.musicplayer.components.albums.AlbumsCarousel
import com.byrondev.musicplayer.components.albums.ButtonsPlayAlbum
import com.byrondev.musicplayer.components.globals.TextLarge
import com.byrondev.musicplayer.components.songs.SongCardWithCover
import com.byrondev.musicplayer.components.topbar.CenterTopAppBar
import com.byrondev.musicplayer.data.models.Song
import com.byrondev.musicplayer.viewModels.MusicViewModels
import com.byrondev.musicplayer.viewModels.PlayerViewModels

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun FavoritesScreen(
    navController: NavController,
    musicViewModels: MusicViewModels,
    playerViewModels: PlayerViewModels,
) {

    val albumsFavorite = musicViewModels.albumsFavorites.collectAsState()
    val favoriteSongs = musicViewModels.favoritesSong.collectAsState()

    LaunchedEffect(favoriteSongs.value.size) {
        playerViewModels.updateCurrentListSongs(favoriteSongs.value)
    }

    LaunchedEffect (favoriteSongs.value){
        musicViewModels.getFavoritesSong()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            CenterTopAppBar(
                title = stringResource(id = R.string.favorites),
                iconColor = Color.White,
            ) { navController.popBackStack() }
            LazyColumn (
                modifier = Modifier.padding(horizontal = 5.dp)
            ) {
                item {
                    if (albumsFavorite.value.isNotEmpty()) {
                        AlbumsCarousel(
                            albumsFavorite.value,
                            navController,
                            text1 = stringResource(id=R.string.favorites_albums),
                            text2 = "",
                        ) { }
                    }
                }
                if (favoriteSongs.value.isNotEmpty()) {
                    item {
                        Column(
                            modifier = Modifier.padding(vertical = 10.dp),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            TextLarge(stringResource(id=R.string.favorites_songs), color = Color.White)
                            ButtonsPlayAlbum(playerViewModels)
                        }
                    }
                    itemsIndexed(favoriteSongs.value) { index: Int, item: Song ->
                        Column(modifier = Modifier.padding(vertical = 3.dp)) {
                            SongCardWithCover(
                                song = item,
                                index = index,
                                navController,
                                playerViewModels,
                                musicViewModels
                            )
                        }
                    }
                }
            }
        }
    }
}