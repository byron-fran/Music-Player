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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.byrondev.musicplayer.viewModels.MusicViewModels

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun ArtistScreen (musicViewModels: MusicViewModels, navController: NavController) {

//    val listArtist = musicViewModels.artists.collectAsState().value Todo update this

    Column (modifier = Modifier.background(color = Color.Black).fillMaxSize()){

        LazyVerticalGrid  (
            columns =   GridCells.Adaptive(170.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(16.dp)
        ) {

            // Todo Change this
            items(20) { artist ->
//                 ArtistCard(artist, navController)
            }
        }
    }

}
