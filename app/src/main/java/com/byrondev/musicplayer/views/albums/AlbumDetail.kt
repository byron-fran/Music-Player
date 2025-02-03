package com.byrondev.musicplayer.views.albums

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.palette.graphics.Palette
import com.byrondev.musicplayer.components.albums.AlbumDescription
import com.byrondev.musicplayer.components.albums.ButtonsPlayAlbum
import com.byrondev.musicplayer.components.globals.HeaderContent
import com.byrondev.musicplayer.components.globals.TopAppBarLeft
import com.byrondev.musicplayer.components.songs.SongCard
import com.byrondev.musicplayer.data.models.Album
import com.byrondev.musicplayer.ui.theme.Slate80
import com.byrondev.musicplayer.utils.bitmap.getByteArray
import com.byrondev.musicplayer.utils.decodeBitmapWithSubsampling
import com.byrondev.musicplayer.viewModels.MusicViewModels
import com.byrondev.musicplayer.viewModels.PlayerViewModels
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun AlbumDetail(
    navController: NavController,
    musicViewModels: MusicViewModels,
    playerViewModels: PlayerViewModels,
    id: Int,
) {
    val albumWithSongs by musicViewModels.albumWithSongs.collectAsState()
    val lazyListState = rememberLazyListState()
    val scrollOffset = remember { derivedStateOf { lazyListState.firstVisibleItemIndex > 0} }
    val songs = albumWithSongs?.songs ?: emptyList()
    val album = albumWithSongs?.album ?: Album()
    val songsOrderedByTrack = songs.sortedBy { it.trackNumber ?: Int.MAX_VALUE }
    val context = LocalContext.current

    LaunchedEffect(id) {
        musicViewModels.clearAlbumWithSongs()
        musicViewModels.getAlbumByIdWithSongs(id)
    }

    LaunchedEffect(albumWithSongs?.songs) {
        if (albumWithSongs?.songs != null) {
            playerViewModels.updateCurrentListSongs(songs.sortedBy {
                it.trackNumber ?: Int.MAX_VALUE
            })
        }
    }
    if (albumWithSongs?.album != null) {

        val coverArt = getByteArray(album.cover, context )
        val imageBitmap = remember { coverArt?.let {  decodeBitmapWithSubsampling(it, 300, 300) } }
        val palette = imageBitmap?.let { Palette.from(it).generate() }
        val dominant = palette?.getDominantColor(Slate80.toArgb())
        val vibrant = palette?.getVibrantColor(Slate80.toArgb())
        val colors = listOf( Color(dominant ?: 0),Color(vibrant ?: 0),  Color.Black, Color.Black, Color.Black)

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = Brush
                    .verticalGradient(colors = colors), alpha = 0.9f)
        ) {
            Column {
                TopAppBarLeft(
                    album.title,
                    scrollOffset,
                    modifier = Modifier.height(90.dp)
                ) { navController.popBackStack() }
                LazyColumn(
                    state = lazyListState,
                    modifier = Modifier.fillMaxSize()

                ) {
                    item {
                        HeaderContent(
                            title = album.title,
                            bytesArray = listOf(coverArt),
                            texts = listOf(album.artist, album.year, album.genres),
                            navController = navController
                        )
                        ButtonsPlayAlbum(
                            playerViewModels,
                            modifier = Modifier.padding(vertical = 20.dp, horizontal = 10.dp)
                        )
                    }
                    itemsIndexed(songsOrderedByTrack) { index, song ->
                        SongCard(song, navController = navController) {
                            playerViewModels.viewModelScope.launch {
                                playerViewModels.playSeekTo(index)
                            }
                        }
                    }
                    item {
                        AlbumDescription(album)
                        Spacer(modifier = Modifier.height(15.dp))
                    }
                }
            }
        }
    }
}