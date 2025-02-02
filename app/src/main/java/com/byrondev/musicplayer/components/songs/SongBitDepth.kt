package com.byrondev.musicplayer.components.songs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.byrondev.musicplayer.R
import com.byrondev.musicplayer.components.globals.TextMedium
import com.byrondev.musicplayer.data.models.Song
import com.byrondev.musicplayer.ui.theme.Pink60
import com.byrondev.musicplayer.ui.theme.Yellow50
import com.byrondev.musicplayer.utils.textSampleRate

@Composable
fun SongBitDepth ( song: Song) {

        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,

        ){
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp),

            ){
                if (song.audioBitDepth != null && song.sampleRate != 0) {
                    song.audioBitDepth.let {
                        Icon(
                            painter = painterResource(id= R.drawable.baseline_audio_quality_30),
                            modifier = Modifier.size(30.dp),
                            tint = if(it >= 24) Yellow50 else Pink60,
                            contentDescription = "Show icon lossless",

                            )
                        CardSongBitDepth("Lossless " + song.sampleRate.toString().textSampleRate(it), it)
                    }
                }
            }
        }
}


@Composable
fun CardSongBitDepth(text : String, bitDepth : Int) {
    Card (
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier.padding(vertical = 10.dp),

    ){ TextMedium(text, color = if(bitDepth >= 24) Yellow50 else Pink60 ) }
}