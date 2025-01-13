package com.byrondev.musicplayer.components.songs

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.byrondev.musicplayer.components.albums.ButtonsPlayAlbum
import com.byrondev.musicplayer.components.globals.EmptyScreen
import com.byrondev.musicplayer.components.images.CoverImage
import com.byrondev.musicplayer.data.models.Song
import com.byrondev.musicplayer.viewModels.PlayerViewModels


@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun SongList(
    songs : List<Song>,
    showTrackNumber : Boolean = true,
    paddingValues: PaddingValues,
    playerViewModels : PlayerViewModels
    ){

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
                        CoverImage(song.cover, Modifier.height(55.dp).width(55.dp).align(Alignment.CenterVertically).padding(vertical = 2.dp), contentScale = ContentScale.Fit)
                        SongCard (song,showTrackNumber,) {
                            // Todo add Event player
                        }
                    }
                }
            }
        }
        else {
            EmptyScreen("Empty songs")
        }
    }
}