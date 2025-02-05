package com.byrondev.musicplayer.views.artists

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.byrondev.musicplayer.components.globals.EmptyScreen
import com.byrondev.musicplayer.components.globals.TextLarge
import com.byrondev.musicplayer.ui.theme.Zinc40
import com.byrondev.musicplayer.viewModels.MusicViewModels

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun ArtistScreen(musicViewModels: MusicViewModels, navController: NavController) {

    val listArtist = musicViewModels.artists.collectAsState()

    if (listArtist.value.isNotEmpty()) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .offset(x = 0.dp, y = 10.dp)
                .padding(horizontal = 5.dp)
                .fillMaxSize()
        ) {
            items(listArtist.value) {
                ArtistCard(artist = it) { navController.navigate("ArtistDetail/${it.id}") }
            }
        }
    } else {

        EmptyScreen() { TextLarge("Artists empty", color = Zinc40) }
    }
}
