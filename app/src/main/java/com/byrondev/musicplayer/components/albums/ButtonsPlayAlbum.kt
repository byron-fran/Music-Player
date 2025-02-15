package com.byrondev.musicplayer.components.albums

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import com.byrondev.musicplayer.R
import com.byrondev.musicplayer.components.globals.IconSmall
import com.byrondev.musicplayer.components.globals.TextMedium
import com.byrondev.musicplayer.ui.theme.Pink60
import com.byrondev.musicplayer.ui.theme.Slate80
import com.byrondev.musicplayer.viewModels.PlayerViewModels
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun ButtonsPlayAlbum(
    playerViewModels: PlayerViewModels,
    modifier: Modifier = Modifier,

    ) {

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically

    ) {
        Button(
            onClick = {
                playerViewModels.viewModelScope.launch {
                    playerViewModels.play()
                }
            },
            modifier = Modifier
                .clip(RoundedCornerShape(5.dp))
                .weight(1f),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Slate80)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                IconSmall(R.drawable.baseline_play_arrow_30, tint = Pink60) { }
                TextMedium(text = stringResource(id = R.string.button_play), color = Pink60)
            }
        }
        Button(
            onClick = { /* Todo add event */ },
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .weight(1f),
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Slate80),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {

                IconSmall(R.drawable.baseline_shuffle_30, tint = Pink60) { }
                TextMedium(text = stringResource(id = R.string.button_shuffle), color = Pink60)
            }
        }
    }
}