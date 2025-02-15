package com.byrondev.musicplayer.components.playlist

import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.byrondev.musicplayer.R
import com.byrondev.musicplayer.components.globals.ImageDefault
import com.byrondev.musicplayer.components.globals.LazyImageCover
import com.byrondev.musicplayer.data.models.Playlist

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun PlaylistItem(playlist: Playlist, onClick: () -> Unit) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.clickable { onClick() }
    ) {
        Box(
            modifier = Modifier
                .width(50.dp)
                .height(50.dp)
                .background(
                    brush = Brush
                        .verticalGradient(
                            colors = listOf(
                                Color(playlist.color1),
                                Color(playlist.color2)
                            )
                        ),
                    shape = RoundedCornerShape(5.dp),
                    alpha = 0.6f
                ),
            contentAlignment = Alignment.Center
        ) {
            LazyImageCover(playlist.uri1) {
                ImageDefault(
                    R.drawable.baseline_music_note_600,
                    modifier = Modifier.size(40.dp)
                )
            }
        }
        PlaylistInfo(playlist)
    }
}