package com.byrondev.musicplayer.components.songs

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.byrondev.musicplayer.R
import com.byrondev.musicplayer.components.globals.CircleSeparation
import com.byrondev.musicplayer.components.globals.IconSmall
import com.byrondev.musicplayer.components.globals.LazyImageCover
import com.byrondev.musicplayer.components.globals.TextExtraSmall
import com.byrondev.musicplayer.components.globals.TextMedium
import com.byrondev.musicplayer.components.menu.MenuItem
import com.byrondev.musicplayer.data.models.Song
import com.byrondev.musicplayer.layout.SheetModalLayout
import com.byrondev.musicplayer.utils.dates.formatDuration
import com.byrondev.musicplayer.viewModels.MusicViewModels
import com.byrondev.musicplayer.viewModels.PlayerViewModels
import kotlinx.coroutines.launch


@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun SongCard(
    song: Song,
    showTrackNumber: Boolean = true,
    navController: NavController,
    musicViewModels: MusicViewModels,
    playerViewModels: PlayerViewModels,
    cardHeight: Dp = 50.dp,
    color: Color = Color.Transparent,
    onClick: () -> Unit,
) {

    val showModalSheet = remember { mutableStateOf(false) }

    Card(
        onClick = { onClick() },
        modifier = Modifier
            .fillMaxWidth()
            .height(cardHeight),
        colors = CardDefaults.cardColors(containerColor = color),
        shape = RoundedCornerShape(0.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom,

            ) {
            SongCardInfo(song, showTrackNumber, modifier = Modifier.weight(1f))
            ShowQualityAudio(
                song,
                showModalSheet,
                playerViewModels,
                musicViewModels,
                navController
            )
        }
    }

}

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun ShowQualityAudio(
    song: Song,
    showModalSheet: MutableState<Boolean>,
    playerViewModels: PlayerViewModels,
    musicViewModels: MusicViewModels,
    navController: NavController,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        if (song.audioBitDepth != null) {
            if (song.audioBitDepth >= 24) {
                Image(
                    painter = painterResource(id = R.drawable.hi_res_logo),
                    contentDescription = "",
                    modifier = Modifier.size(25.dp)
                )
            }
        }
        IconSmall(R.drawable.more_vert_30, tint = Color.White) { showModalSheet.value = true }
        SheetModalLayout(showModalSheet) {
            SongOptionsItems(
                playerViewModels,
                musicViewModels,
                navController,
                song,
                showModalSheet
            ) {
                MenuItem(R.drawable.baseline_play_arrow_30, R.string.song_play_next) {
                    // Todo add event
                }
            }
        }
    }
}

@Composable
fun SongCardInfo(song: Song, showTrackNumber: Boolean, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (showTrackNumber) {
            TextExtraSmall(text = "${song.trackNumber ?: "1"}", modifier = Modifier.width(20.dp))
        }
        Column(
            modifier = Modifier.padding(start = 10.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            TextMedium(song.title ?: "", color = Color.White)
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                TextExtraSmall(text = song.artist ?: "Unknown")
                CircleSeparation()
                TextExtraSmall(text = formatDuration(song.duration.toInt()))
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun SongCardWithCover(
    song: Song, index: Int,
    navController: NavController,
    playerViewModels: PlayerViewModels,
    musicViewModels: MusicViewModels,
) {
    Row(

    ) {
        LazyImageCover(
            song.uri,
            imageDefault = painterResource(R.drawable.image_music_default),
            modifier = Modifier.size(50.dp)
        )
        SongCard(
            song,
            showTrackNumber = false,
            navController,
            musicViewModels,
            playerViewModels
        ) {
            playerViewModels.viewModelScope.launch {
                playerViewModels.playSeekTo(index)
            }
        }
    }
}