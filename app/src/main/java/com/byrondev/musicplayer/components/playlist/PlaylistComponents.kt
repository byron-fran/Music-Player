package com.byrondev.musicplayer.components.playlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.byrondev.musicplayer.components.globals.CircleSeparation
import com.byrondev.musicplayer.components.globals.TextExtraSmall
import com.byrondev.musicplayer.components.globals.TextMedium
import com.byrondev.musicplayer.data.models.Playlist

@Composable
fun PlaylistInfo (playlist: Playlist, modifier: Modifier = Modifier) {
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ){
        TextMedium(playlist.name)
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            TextExtraSmall(text="30 canciones")
            CircleSeparation()
            TextExtraSmall(text="58:00")
        }
    }
}