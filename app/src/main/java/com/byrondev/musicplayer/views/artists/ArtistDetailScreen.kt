package com.byrondev.musicplayer.views.artists

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.byrondev.musicplayer.R
import com.byrondev.musicplayer.components.albums.AlbumsCarousel
import com.byrondev.musicplayer.components.albums.ButtonsPlayAlbum
import com.byrondev.musicplayer.components.globals.LargeImage
import com.byrondev.musicplayer.components.songs.SongCardWithCover
import com.byrondev.musicplayer.components.texts.TextRowSeparation
import com.byrondev.musicplayer.components.topbar.CenterTopAppBar
import com.byrondev.musicplayer.ui.theme.Slate80
import com.byrondev.musicplayer.viewModels.MusicViewModels
import com.byrondev.musicplayer.viewModels.PlayerViewModels

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
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column {
            CenterTopAppBar(
                title = artist?.name ?: stringResource(R.string.unknown_artist),
                iconColor = Color.White
            ) { navController.popBackStack() }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.verticalScroll(state = scrollState)
            ) {
                Box(
                    modifier = Modifier.background(Slate80, shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    LargeImage(id = R.drawable.artist_200, modifier = Modifier.padding(10.dp))
                }
                TextRowSeparation(
                    text1 = stringResource(R.string.library_songs),
                    text2 = stringResource(R.string.see_more),
                    modifier = Modifier.padding(
                        top = 30.dp,
                        start = 15.dp,
                        end = 20.dp,
                        bottom = 10.dp
                    ),
                    onClick = { navController.navigate("SongsByArtist/${id}") }
                )
                Column(
                    modifier = Modifier.padding(horizontal = 5.dp)
                ) {
                    ButtonsPlayAlbum(playerViewModels, modifier = Modifier.padding(bottom = 15.dp))
                    limitedItems.forEachIndexed { index, song ->
                        SongCardWithCover(
                            song, index,
                            navController,
                            playerViewModels,
                            musicViewModels
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
                if (albumsType.isNotEmpty()) {
                    AlbumsCarousel(
                        albumsType,
                        navController,
                        text1 = stringResource(R.string.library_albums),
                        text2 = stringResource(R.string.see_more)
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
