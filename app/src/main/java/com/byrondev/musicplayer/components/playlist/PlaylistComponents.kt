package com.byrondev.musicplayer.components.playlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.byrondev.musicplayer.components.globals.CircleSeparation
import com.byrondev.musicplayer.components.globals.TextExtraSmall
import com.byrondev.musicplayer.components.globals.TextMedium
import com.byrondev.musicplayer.data.dao.PlaylistWithCountSong
import com.byrondev.musicplayer.utils.dates.formatDuration

@Composable
fun PlaylistInfo (playlist: PlaylistWithCountSong, modifier: Modifier = Modifier) {
    Column (
        modifier = modifier.height(40.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ){
        TextMedium(playlist.name)
        if(playlist.songCount > 0) {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                TextExtraSmall(text="${playlist.songCount} songs")
                CircleSeparation()
                TextExtraSmall(text= formatDuration(playlist.duration))
            }
        }
    }
}