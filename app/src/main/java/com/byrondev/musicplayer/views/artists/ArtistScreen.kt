package com.byrondev.musicplayer.views.artists

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.byrondev.musicplayer.components.globals.EmptyScreen
import com.byrondev.musicplayer.components.globals.TextLarge
import com.byrondev.musicplayer.ui.theme.Zinc40
import com.byrondev.musicplayer.viewModels.MusicViewModels

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun ArtistScreen (musicViewModels: MusicViewModels, navController: NavController) {

    val listArtist = musicViewModels.artists.collectAsState()

    LaunchedEffect(Unit) {
        musicViewModels.getAllArtists()
    }

    Column (modifier = Modifier.background(color = Color.Black).fillMaxSize()){
        if(listArtist.value.isNotEmpty()){
            LazyVerticalGrid  (
                columns =   GridCells.Adaptive(170.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(listArtist.value) { artist ->
                    ArtistCard(artist, navController)
                }
            }
        }
        else {
            EmptyScreen(){ TextLarge("Artists empty", color= Zinc40) }
        }
    }

}
