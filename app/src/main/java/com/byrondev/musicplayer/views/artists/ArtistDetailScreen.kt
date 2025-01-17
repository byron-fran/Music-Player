package com.byrondev.musicplayer.views.artists

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.byrondev.musicplayer.R
import com.byrondev.musicplayer.components.BottomBar
import com.byrondev.musicplayer.components.albums.AlbumCard
import com.byrondev.musicplayer.components.songs.SongCard
import com.byrondev.musicplayer.components.texts.TextRowSeparation
import com.byrondev.musicplayer.components.topbar.CenterTopAppBar
import com.byrondev.musicplayer.data.models.Album
import com.byrondev.musicplayer.data.models.Song
import com.byrondev.musicplayer.ui.theme.Slate70
import com.byrondev.musicplayer.viewModels.MusicViewModels
import com.byrondev.musicplayer.viewModels.PlayerViewModels

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun ArtistDetailScreen(navController: NavController, musicViewModels: MusicViewModels,playerViewModels :PlayerViewModels, id : Int) {

    val artistWithSongs  by musicViewModels.artistWithSongs.collectAsState()
    val artistWithAlbums  by musicViewModels.artistWithAlbums.collectAsState()
    val artist = artistWithAlbums?.artist
    val albums = artistWithAlbums?.albums?.sortedByDescending { it.year?.substringBefore("-")?.toInt() } ?: emptyList()
    val songs = artistWithSongs?.songs ?: emptyList()

    LaunchedEffect(id) {

        musicViewModels.getArtistWithSongs(id)
        musicViewModels.getArtistWithAlbums(id)
        musicViewModels.clearArtistWithSongsAndAlbums()

    }

    Scaffold (
        topBar = {  CenterTopAppBar( artist?.name ?: "Arist unknown", Icons.AutoMirrored.Default.ArrowBack){navController.popBackStack()} },
        bottomBar = { BottomBar(navController) }
    ){ paddingValues ->
        ArtistDetailScreenContent(paddingValues, navController, songs, albums, id)
    }

}

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun ArtistDetailScreenContent(
    paddingValues: PaddingValues,
    navController: NavController,
    songs : List<Song>,
    albums : List<Album>,
    id : Int,
) {

    val limitedItems = songs.take(5);

    LazyColumn (modifier = Modifier.padding(paddingValues).background(Color.Black).fillMaxSize()) {
        item {
            Column (
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
                ) {
                Card(
                    shape = CircleShape,
                    colors =  CardDefaults.cardColors(containerColor = Slate70),
                ) {
                    Image(
                        painter = painterResource(id=R.drawable.baseline_person_24),
                        contentDescription = "",
                        modifier = Modifier.padding(10.dp),
                    )
                }
            }
            TextRowSeparation(
                "Songs",
                "View all",
                modifier = Modifier.padding(top=30.dp, start = 15.dp, end = 20.dp, bottom = 10.dp),
                onClick = {navController.navigate("SongsByArtist/${id}")}
                )

        }
        itemsIndexed(limitedItems){ index, song ->
           SongCard(song, false,navController) { /* Todo add event player */ }

        }
        item {
            if(albums.isNotEmpty()) {
                TextRowSeparation(
                    "Albums",
                    "View all",
                    modifier = Modifier
                        .padding(top=30.dp, start = 15.dp, end = 20.dp, bottom = 20.dp),
                    onClick = {navController.navigate("AlbumsByArtist/${id}")}
                )
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(albums){ album ->
                        AlbumCard(album, navController, modifier = Modifier.width(185.dp))
                    }
                }
            }
        }
    }
}
