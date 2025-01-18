package com.byrondev.musicplayer.components.playlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.byrondev.musicplayer.components.texts.TextRowSeparation
import com.byrondev.musicplayer.data.dao.PlaylistWithCountSong

@Composable
fun PlaylistCarousel(playlistItems : List<PlaylistWithCountSong>, navController: NavController) {

    Column {
        TextRowSeparation(
            "Playlists",
            "view more",
            modifier = Modifier.padding(start = 10.dp, end = 5.dp, bottom = 15.dp ),
            onClick = {navController.navigate("PlaylistScreen/${0}")}
            )
        LazyRow (horizontalArrangement = Arrangement.spacedBy(15.dp)) {
            items(playlistItems) {
                PlaylistCard(it) {navController.navigate("PlaylistDetailScreen/${it.id}")}
            }
        }
    }
}