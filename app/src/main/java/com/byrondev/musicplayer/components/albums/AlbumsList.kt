package com.byrondev.musicplayer.components.albums

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.byrondev.musicplayer.components.globals.EmptyScreen
import com.byrondev.musicplayer.components.globals.TextLarge
import com.byrondev.musicplayer.data.dao.AlbumResponse
import com.byrondev.musicplayer.ui.theme.Zinc40


@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun AlbumsList(
    albums: List<AlbumResponse>,
    navController: NavController,
    paddingValues: PaddingValues,
) {

    if (albums.isNotEmpty()) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(170.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(16.dp),

            ) {

            items(albums) { album ->
                AlbumCard(album, navController)
            }
        }

    } else {
        EmptyScreen() { TextLarge("Empty albums", color = Zinc40) }
    }
}