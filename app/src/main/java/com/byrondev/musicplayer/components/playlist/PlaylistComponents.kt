package com.byrondev.musicplayer.components.playlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.byrondev.musicplayer.R
import com.byrondev.musicplayer.components.globals.CircleSeparation
import com.byrondev.musicplayer.components.globals.TextExtraSmall
import com.byrondev.musicplayer.components.globals.TextMedium
import com.byrondev.musicplayer.data.models.Playlist
import com.byrondev.musicplayer.utils.dates.formatDuration

@Composable
fun PlaylistInfo(playlist: Playlist, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.height(40.dp).padding(top=5.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        TextMedium(playlist.name, color = Color.White)
        if (playlist.numberOfSongs > 0) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                if (playlist.numberOfSongs > 1)
                    TextExtraSmall(text = "${playlist.numberOfSongs} " + stringResource(R.string.library_songs))
                else
                    TextExtraSmall(text = "${playlist.numberOfSongs} " + stringResource(R.string.song))

                CircleSeparation()
                TextExtraSmall(text = formatDuration(playlist.duration.toInt()))
            }
        }
    }
}