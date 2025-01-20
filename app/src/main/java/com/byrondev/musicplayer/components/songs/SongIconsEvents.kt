package com.byrondev.musicplayer.components.songs

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.byrondev.musicplayer.R
import com.byrondev.musicplayer.components.globals.TextExtraSmall
import com.byrondev.musicplayer.components.globals.TextLarge
import com.byrondev.musicplayer.data.models.Song
import com.byrondev.musicplayer.viewModels.MusicViewModels

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun SongIconsEvents(
    song: Song,
    musicViewModels: MusicViewModels,
    navController: NavController,
    ) {

    val isFavorite = remember { mutableStateOf(song.isFavorite) }

    Column (
        modifier = Modifier.offset(x= 0.dp, y =30.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_playlist_30),
                modifier = Modifier.size(30.dp).clickable { navController.navigate("PlaylistScreen/${song.id}") },
                contentDescription = "",
                tint = Color.White
            )
            Column (
               horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                TextLarge(text = song.title ?: "", modifier = Modifier.width(300.dp))
                TextExtraSmall(text=song.album ?: "Album unknown",modifier = Modifier.width(300.dp))
            }
            Icon(
                imageVector = if (isFavorite.value)  Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = "",
                modifier = Modifier.size(30.dp).clickable {
                    isFavorite.value = !isFavorite.value
                    musicViewModels.updateIsFavoriteSong(song.id, isFavorite.value)
                },
                tint = Color.White
            )
        }
    }
}