package com.byrondev.musicplayer.views.playlist

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.byrondev.musicplayer.components.BottomBar
import com.byrondev.musicplayer.components.globals.HeaderContent
import com.byrondev.musicplayer.components.songs.SongList
import com.byrondev.musicplayer.utils.dates.formatDuration
import com.byrondev.musicplayer.viewModels.MusicViewModels
import com.byrondev.musicplayer.viewModels.PlayerViewModels

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun PlaylistDetailScreen(
    navController: NavController,
    musicViewModels: MusicViewModels,
    playerViewModels: PlayerViewModels,
    id : Int
    ) {

    val playlistWithSongs = musicViewModels.playlistWithSongs.collectAsState()

    val lazyListState = rememberLazyListState()

    val scrollOffset = remember {
        derivedStateOf { lazyListState.firstVisibleItemScrollOffset > 0 }
    }
    val targetHeight: Dp by remember {
        derivedStateOf { if (scrollOffset.value) 80.dp else 370.dp }
    }
    val animatedHeight by animateDpAsState(targetValue = targetHeight, label = "",)

    LaunchedEffect(id) {
        musicViewModels.getPlaylistWithSongs(id)
    }

    Box(
        modifier = Modifier.fillMaxSize().background(Color.Black),
    ) {
        Column (
            modifier = Modifier.fillMaxSize()
        ) {
            playlistWithSongs.value.forEach {
                val totalDuration = it.songs.sumOf { s -> s.duration }
                HeaderContent(
                    title = it.playlist.name,
                    texts = listOf("${it.songs.count()} songs", formatDuration(totalDuration.toInt())),
                    height = animatedHeight,
                    scrollOffset = scrollOffset,
                    navController =navController,
                    bytesArray = listOf(ByteArray(2))
                )
                SongList(
                    songs=it.songs,
                    playerViewModels=playerViewModels,
                    navController = navController,
                    paddingValues = PaddingValues(0.dp),
                    showTrackNumber = false,
                )
            }
        }

        BottomBar(navController, modifier = Modifier.align(Alignment.BottomStart))
    }
}

