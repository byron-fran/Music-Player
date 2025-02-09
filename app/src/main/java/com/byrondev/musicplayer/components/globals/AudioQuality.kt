package com.byrondev.musicplayer.components.globals

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.byrondev.musicplayer.ui.theme.Pink60
import com.byrondev.musicplayer.ui.theme.Yellow50
import com.byrondev.musicplayer.utils.textSampleRate

@Composable
fun AudioQuality(
    audioBitDepth: Int?,
    audioSampleRate: Int?,
    audioFormat :String?,
    modifier: Modifier = Modifier
) {
    Row (
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
        ){
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp),

            ){
            if(audioBitDepth != null) {
                if (audioBitDepth > 0 && audioSampleRate != 0) {

                    audioBitDepth.let {
                        Icon(
                            painter = painterResource(id= R.drawable.baseline_audio_quality_30),
                            modifier = Modifier.size(20.dp),
                            tint = if(it >= 24) Yellow50 else Pink60,
                            contentDescription = "Show icon lossless",

                            )
                        CardSongBitDepth("${audioFormat?.uppercase()} " + audioSampleRate.toString().textSampleRate(it), it)
                    }
                }
                else {
                    if(audioFormat != null && audioSampleRate != 0) {
                        TextExtraSmall(text="${audioFormat.uppercase()} - ${audioSampleRate.toString().textSampleRate(null)} ")
                    }
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
        modifier = Modifier,
        ){ TextExtraSmall(text=text, color = if(bitDepth >= 24) Yellow50 else Pink60 ) }
}