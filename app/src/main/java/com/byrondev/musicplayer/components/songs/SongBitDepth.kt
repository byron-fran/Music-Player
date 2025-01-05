package com.byrondev.musicplayer.components.songs

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.byrondev.musicplayer.data.models.Song
import com.byrondev.musicplayer.ui.theme.Blue70
import com.byrondev.musicplayer.ui.theme.Blue80
import com.byrondev.musicplayer.ui.theme.Blue95
import com.byrondev.musicplayer.ui.theme.Yellow50
import com.byrondev.musicplayer.ui.theme.Yellow80
import com.byrondev.musicplayer.ui.theme.Yellow95
import com.byrondev.musicplayer.ui.theme.textDarkGray13
import com.byrondev.musicplayer.utils.textSampleRate

@Composable
fun SongBitDepth ( song: Song) {

        if (song.audioBitDepth != 0 && song.sampleRate != 0) {
            when (song.audioBitDepth!!) {
                16 -> {
                    CardSongBitDepth(
                        "CD " + song.sampleRate.toString().textSampleRate(song.audioBitDepth!!),
                        Blue70, Blue80, Blue95
                    )
                }

                24 -> {
                    CardSongBitDepth(
                        "Hi-res " + song.sampleRate.toString().textSampleRate(song.audioBitDepth!!),
                        Yellow50, Yellow80, Yellow95,
                        )
                }
                else -> { Text("") }
            }
        }
}

@Composable
fun CardSongBitDepth(text : String, color1 : Color, color2 : Color, color3 : Color) {
    Card (
        colors = CardDefaults.cardColors(containerColor = color3),
        border = BorderStroke(1.dp, color2),
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier.padding(vertical = 10.dp),

    ){
        Text(text, color = color1, modifier = Modifier.padding(10.dp), style = textDarkGray13,)
    }
}