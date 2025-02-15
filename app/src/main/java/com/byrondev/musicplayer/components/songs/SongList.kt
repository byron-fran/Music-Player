package com.byrondev.musicplayer.components.songs

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.byrondev.musicplayer.components.albums.ButtonsPlayAlbum
import com.byrondev.musicplayer.components.globals.EmptyScreen
import com.byrondev.musicplayer.data.models.Song
import com.byrondev.musicplayer.viewModels.MusicViewModels
import com.byrondev.musicplayer.viewModels.PlayerViewModels

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun SongList(
    songs: List<Song>,
    playerViewModels: PlayerViewModels,
    navController: NavController,
    musicViewModels: MusicViewModels,
) {

    LaunchedEffect(songs.size) {
        playerViewModels.updateCurrentListSongs(songs)
    }

    if (songs.isNotEmpty()) {
        LazyColumn(
            modifier = Modifier
                .padding(vertical = 10.dp, horizontal = 5.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            item {
                ButtonsPlayAlbum(
                    playerViewModels,
                    modifier = Modifier.padding(bottom = 5.dp)
                )
            }
            itemsIndexed(songs) { index, song ->
                SongCardWithCover(
                    song, index,
                    navController,
                    playerViewModels,
                    musicViewModels
                )
            }
        }
    } else {
        EmptyScreen() {
            // Todo add content to show when songs is empty
        }
    }
}