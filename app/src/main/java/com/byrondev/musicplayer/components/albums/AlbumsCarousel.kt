package com.byrondev.musicplayer.components.albums

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.byrondev.musicplayer.components.texts.TextRowSeparation
import com.byrondev.musicplayer.data.models.Album

@Composable
fun AlbumsCarousel(
    albums : List<Album>,
    navController : NavController,
    text1: String,
    text2 : String,
    onClick : () -> Unit
) {
    TextRowSeparation(
        text1,
        text2,
        modifier = Modifier
            .padding(10.dp),
        onClick = {onClick()}
    )
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp)
    ) {
        items(albums){ album ->
            AlbumCard(album, navController, modifier = Modifier.width(185.dp))
        }
    }
}