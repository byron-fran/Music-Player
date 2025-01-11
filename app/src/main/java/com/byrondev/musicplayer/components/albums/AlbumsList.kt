package com.byrondev.musicplayer.components.albums

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.byrondev.musicplayer.components.globals.EmptyScreen
import com.byrondev.musicplayer.data.models.Album


@Composable
fun AlbumsList(albums: List<Album>, navController: NavController, paddingValues : PaddingValues) {

    if(albums.isNotEmpty()){
        Column (modifier = Modifier.background(color = Color.Black).padding(paddingValues).fillMaxSize()){
            LazyVerticalGrid   (
                columns = GridCells.Adaptive(170.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(16.dp),

                ) {

                items(albums){album ->
                    AlbumCard(album, navController)
                }
            }
        }
    }
    else {
        EmptyScreen("Empty albums")
    }
}