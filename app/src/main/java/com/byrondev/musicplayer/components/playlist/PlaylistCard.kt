package com.byrondev.musicplayer.components.playlist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.byrondev.musicplayer.R
import com.byrondev.musicplayer.components.globals.IconSmall
import com.byrondev.musicplayer.components.globals.MediumImage
import com.byrondev.musicplayer.data.models.Playlist
import com.byrondev.musicplayer.ui.theme.Slate80

@Composable
fun PlaylistCard(
    playlist: Playlist,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {

    Box(
        modifier = modifier.clickable { onClick() },
        contentAlignment = Alignment.Center,
    ) {
        Column {
            Box(
                modifier = Modifier
                    .aspectRatio(1f)
                    .fillMaxWidth()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(playlist.color1),
                                Color(playlist.color2),
                            )
                        ),
                        shape = RoundedCornerShape(5.dp),
                        alpha = 0.6f
                    )
                ,
                contentAlignment = Alignment.Center
            ) {
                MediumImage(R.drawable.playlist_100, tint = Color.White)
                IconButton(
                    modifier = Modifier.align(Alignment.BottomEnd),
                    onClick = {},
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Slate80
                    )
                ) {
                    IconSmall(R.drawable.baseline_play_arrow_30, tint = Color.White) { }
                }
            }
            PlaylistInfo(playlist, modifier = Modifier)
        }
    }
}