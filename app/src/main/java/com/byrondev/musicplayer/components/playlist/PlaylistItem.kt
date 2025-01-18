package com.byrondev.musicplayer.components.playlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.byrondev.musicplayer.R
import com.byrondev.musicplayer.data.dao.PlaylistWithCountSong
import com.byrondev.musicplayer.ui.theme.Slate80

@Composable
fun PlaylistItem(playlist: PlaylistWithCountSong, onClick : () -> Unit) {

    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.clickable { onClick() }
    ) {
        Box (
            modifier = Modifier
                .width(60.dp)
                .height(60.dp)
                .background(Slate80, shape = RoundedCornerShape(5.dp)),
            contentAlignment = Alignment.Center

        ) {
            // Todo replace image of playlist if has it
            Image(
                painter = painterResource(id = R.drawable.baseline_music_note_600),
                contentDescription = "Icon",
                modifier = Modifier.size(30.dp)
            )
        }
        PlaylistInfo(playlist)
    }
}