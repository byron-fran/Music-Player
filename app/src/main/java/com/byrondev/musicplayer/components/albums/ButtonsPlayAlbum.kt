package com.byrondev.musicplayer.components.albums

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.byrondev.musicplayer.R
import com.byrondev.musicplayer.components.globals.TextMedium
import com.byrondev.musicplayer.ui.theme.Pink60
import com.byrondev.musicplayer.ui.theme.Slate80
import com.byrondev.musicplayer.viewModels.PlayerViewModels

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun ButtonsPlayAlbum(playerViewModels: PlayerViewModels,albumCount : Int, modifier: Modifier = Modifier) {

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically

        ) {
            Button (
                onClick = { playerViewModels.play() },
                modifier = Modifier.clip(RoundedCornerShape(5.dp)).weight(1f),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Slate80)
            ) {
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Icon(
                        painter = painterResource(id=R.drawable.baseline_play_arrow_30),
                        contentDescription = "icon to Play",
                        tint = Pink60,
                        modifier = Modifier.size(30.dp)
                    )
                    TextMedium(text="Play", color = Pink60)
                }
            }

        // Button 2
        Button (
            onClick = { /* Todo add event */ },
            modifier = Modifier.clip(RoundedCornerShape(10.dp)).weight(1f),
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Slate80,),
            border = BorderStroke(1.dp, color = Slate80, )
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Icon(
                    painter = painterResource(id=R.drawable.shuffle),
                    contentDescription = "icon to Play shuffle",
                    tint = Pink60,
                    modifier = Modifier.size(30.dp)
                )
                TextMedium(text="Shuffle ", color = Pink60)
            }
        }
    }
}