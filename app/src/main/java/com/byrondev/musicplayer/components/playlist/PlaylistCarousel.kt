package com.byrondev.musicplayer.components.playlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.byrondev.musicplayer.R
import com.byrondev.musicplayer.components.texts.TextRowSeparation
import com.byrondev.musicplayer.data.models.Playlist

@Composable
fun PlaylistCarousel(playlistItems : List<Playlist>, navController: NavController) {

    Column {
        TextRowSeparation(
            stringResource(R.string.library_playlists),
            stringResource(R.string.see_more),
            modifier = Modifier.padding(10.dp ),
            onClick = {navController.navigate("PlaylistScreen/${0}")}
            )
        LazyRow (
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            modifier = Modifier.padding(horizontal = 10.dp)
            ) {
            items(playlistItems) {
                PlaylistCard(it, modifier = Modifier.width(175.dp)) {navController.navigate("PlaylistDetailScreen/${it.id}")}
            }
        }
    }
}