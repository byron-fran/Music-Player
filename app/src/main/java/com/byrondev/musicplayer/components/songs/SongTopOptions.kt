package com.byrondev.musicplayer.components.songs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.byrondev.musicplayer.data.models.Song

@Composable
fun SongTopOptions(song : Song, showModal: MutableState<Boolean>) {


    Row(
        modifier = Modifier.fillMaxWidth().padding( top = 50.dp, bottom = 25.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            imageVector = Icons.Default.KeyboardArrowDown, contentDescription = "Back",
            modifier = Modifier.size(30.dp).clickable (onClick =  { showModal.value = false }),
            tint = Color.White
        )
        Text(
            "${song.artist}",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Icon(
            imageVector = Icons.Default.MoreVert, contentDescription = "Menu options",
            modifier = Modifier.size(30.dp),
            tint = Color.White
        )

    }
}
