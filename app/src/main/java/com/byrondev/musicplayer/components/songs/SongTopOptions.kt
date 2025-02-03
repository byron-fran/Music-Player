package com.byrondev.musicplayer.components.songs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.byrondev.musicplayer.components.globals.TextLarge
import com.byrondev.musicplayer.data.models.Song

@Composable
fun SongTopOptions(
    song : Song,
    navController: NavController,
    onClick : () -> Unit,
    ) {

    Row(
        modifier = Modifier.fillMaxWidth().padding( top = 50.dp, bottom = 25.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            imageVector = Icons.Default.KeyboardArrowDown, contentDescription = "Back",
            modifier = Modifier.size(25.dp).clickable {onClick()},
            tint = Color.White
        )
        TextLarge(
            text=song.artist ?: "Unknown arist",
            modifier = Modifier
                .widthIn(max = 250.dp)
                .clickable {
                    navController.navigate("ArtistDetail/${song.artistId}")
                    onClick()
                })
        Icon(
            imageVector = Icons.Default.MoreVert, contentDescription = "Menu options",
            modifier = Modifier.size(25.dp).clickable { /* Todo add event */ },
            tint = Color.White
        )
    }
}