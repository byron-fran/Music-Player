package com.byrondev.musicplayer.views.artists

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.byrondev.musicplayer.R
import com.byrondev.musicplayer.components.albums.AlbumsCarousel
import com.byrondev.musicplayer.components.albums.ButtonsPlayAlbum
import com.byrondev.musicplayer.components.globals.LazyImageCover
import com.byrondev.musicplayer.components.songs.SongCard
import com.byrondev.musicplayer.components.texts.TextRowSeparation
import com.byrondev.musicplayer.components.topbar.CenterTopAppBar
import com.byrondev.musicplayer.ui.theme.Slate80
import com.byrondev.musicplayer.viewModels.MusicViewModels
import com.byrondev.musicplayer.viewModels.PlayerViewModels
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun ArtistDetailScreen(
    navController: NavController,
    musicViewModels: MusicViewModels,
    playerViewModels: PlayerViewModels,
    id: Int,
) {
    val artistWithSongs by musicViewModels.artistWithSongs.collectAsState()
    val artistWithAlbums by musicViewModels.artistWithAlbums.collectAsState()
    val artist = artistWithSongs?.arist
    val albums = artistWithAlbums.sortedByDescending { it.year }
    val songs = artistWithSongs?.songs?.sortedByDescending { it.year } ?: emptyList()

    val scrollState = rememberScrollState()
    val limitedItems = songs.take(5);
    val albumsType = albums.filter { (it.tracksTotal ?: 0) > 1 || (it.numTracks ?: 0) > 1 }
    val singles = albums.filter { it.tracksTotal == 1 && it.numTracks == 1 }

    LaunchedEffect(id) {
        musicViewModels.getArtistWithSongs(id)
        musicViewModels.getArtistWithAlbums(id)
        musicViewModels.clearArtistWithSongsAndAlbums()
    }
    LaunchedEffect(songs.size) {
        playerViewModels.updateCurrentListSongs(songs)
    }
    Box(modifier = Modifier.fillMaxSize().background(Color.Black)) {
        Column {
            CenterTopAppBar(
                title = artist?.name ?: "Arist unknown",
                Icons.AutoMirrored.Default.ArrowBack
            ) { navController.popBackStack() }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.verticalScroll(state = scrollState)
            ) {
                Box(
                    modifier = Modifier.background(Slate80, shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.artist_icon),
                        modifier = Modifier.padding(10.dp).size(150.dp),
                        contentDescription = "",
                    )
                }
                TextRowSeparation(
                    text1 = "Canciones",
                    text2 = "Ver todo",
                    modifier = Modifier.padding(top = 30.dp, start = 15.dp, end = 20.dp, bottom = 10.dp),
                    onClick = { navController.navigate("SongsByArtist/${id}") }
                )
                Column(
                    modifier = Modifier.padding(horizontal = 5.dp)
                ) {
                    ButtonsPlayAlbum(playerViewModels, modifier = Modifier.padding(bottom = 15.dp))
                    limitedItems.forEachIndexed { index, song ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            LazyImageCover(
                                uri = song.uri,
                                painterResource(R.drawable.image_music_default),
                                modifier = Modifier.width(50.dp).aspectRatio(1f)
                            )
                            SongCard(song, false, navController) {
                                playerViewModels.viewModelScope.launch {
                                    playerViewModels.playSeekTo(index)
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
                if (albumsType.isNotEmpty()) {
                    AlbumsCarousel(
                        albumsType,
                        navController,
                        text1 = "Albumes",
                        text2 = "Ver todo"
                    ) { navController.navigate("AlbumsByArtist/${id}") }
                }
                if (singles.isNotEmpty()) {
                    AlbumsCarousel(
                        singles,
                        navController,
                        text1 = "Singles",
                        text2 = ""
                    ) { }
                }
                Spacer(modifier = Modifier.height(15.dp))
            }
        }
    }
}
