package com.byrondev.musicplayer.components.playlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.byrondev.musicplayer.R
import com.byrondev.musicplayer.data.dao.PlaylistWithCountSong

@Composable
fun PlaylistCard(
    playlist : PlaylistWithCountSong,
    modifier : Modifier = Modifier,
    onClick : () -> Unit,
    ) {

    Box(
        modifier = modifier.clickable {onClick()},
        contentAlignment = Alignment.Center
    ) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.image_music_default),
                modifier = Modifier.clip(RoundedCornerShape(5.dp)).aspectRatio(1f),
                contentScale = ContentScale.Crop,
                contentDescription = "",
            )
            PlaylistInfo(playlist, modifier = Modifier)
        }
    }
}