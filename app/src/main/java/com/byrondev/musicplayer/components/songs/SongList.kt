package com.byrondev.musicplayer.components.songs

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.byrondev.musicplayer.R
import com.byrondev.musicplayer.components.albums.ButtonsPlayAlbum
import com.byrondev.musicplayer.components.globals.EmptyScreen
import com.byrondev.musicplayer.components.globals.LazyImageCover
import com.byrondev.musicplayer.components.globals.TextLarge
import com.byrondev.musicplayer.data.models.Song
import com.byrondev.musicplayer.ui.theme.Zinc40
import com.byrondev.musicplayer.viewModels.PlayerViewModels
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun SongList(
    songs : List<Song>,
    showTrackNumber : Boolean = true,
    paddingValues: PaddingValues,
    playerViewModels : PlayerViewModels,
    navController: NavController
    ){

    LaunchedEffect(songs.size) {
        playerViewModels.updateCurrentListSongs(songs)
    }

    Column (modifier = Modifier.background(color = Color.Black).fillMaxSize().padding(paddingValues)){
        if(songs.isNotEmpty()) {
            LazyColumn  (
                modifier = Modifier.padding(top = 10.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp),
                ) {
                item {
                    ButtonsPlayAlbum(playerViewModels, songs.count(), modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp))
                }
                itemsIndexed(songs) {index, song ->
                    Row  (verticalAlignment = Alignment.CenterVertically) {
                        LazyImageCover(
                            playerViewModels, song.uri,
                            painterResource(R.drawable.baseline_music_note_600),
                            modifier = Modifier.size(50.dp)
                        )
                        SongCard (song,showTrackNumber, navController) {
                            playerViewModels.viewModelScope.launch {
                                playerViewModels.playSeekTo(index)
                            }
                        }
                    }
                }
            }
        }
        else {
            EmptyScreen(){ TextLarge("Songs empty", color= Zinc40) }
        }
    }
}