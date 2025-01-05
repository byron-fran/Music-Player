package com.byrondev.musicplayer.components.songs

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableFloatState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.media3.common.Player
import com.byrondev.musicplayer.data.models.Song
import com.byrondev.musicplayer.utils.durationToText
import com.byrondev.musicplayer.viewModels.MusicViewModels
import com.byrondev.musicplayer.viewModels.PlayerViewModels
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun SongSlider( song: Song,playerViewModels : PlayerViewModels) {
    val minutes = song.duration / 1000 / 60
    val seconds = song.duration / 1000 % 60

    val isPlaying = playerViewModels.isPlaying.collectAsState()
    val currentPosition = playerViewModels.currentPosition.collectAsState()
    val duration =playerViewModels.duration.collectAsState()
    val sliderPosition = remember { mutableLongStateOf(0) }
    val totalDuration = remember { mutableLongStateOf(0) }

    LaunchedEffect (currentPosition.value, isPlaying.value) {
        delay(1000)
        playerViewModels.updateDuration()
    }

    LaunchedEffect(currentPosition.value) {
        sliderPosition.longValue = currentPosition.value
    }

    LaunchedEffect(duration.value) {
        if (duration.value > 0) {
            totalDuration.longValue = duration.value
        }
    }

    Column ( modifier = Modifier.fillMaxWidth()) {
        TrackSlider(
            value = sliderPosition.longValue.toFloat(),
            onValueChange =  { sliderPosition.longValue = it.toLong() },
            onValueChangeFinished =  { playerViewModels.onValueChangeFinished(sliderPosition.longValue) },
            songDuration = totalDuration.longValue.toFloat()
        )
        val remainTime = totalDuration.longValue - currentPosition.value
        Text(
            text = if (remainTime >= 0) remainTime.durationToText() else "",
            color = Color.White
        )

    }


}

@Composable
fun TrackSlider(
    value: Float,
    onValueChange: (newValue: Float) -> Unit,
    onValueChangeFinished: () -> Unit,
    songDuration: Float
) {
    Slider(
        value = value,
        onValueChange = { onValueChange(it) },
        onValueChangeFinished = { onValueChangeFinished() },
        valueRange = 0f..songDuration,
        colors = SliderDefaults.colors(
            thumbColor = Color.Black,
            activeTrackColor = Color.DarkGray,
            inactiveTrackColor = Color.Gray,
        )
    )
}