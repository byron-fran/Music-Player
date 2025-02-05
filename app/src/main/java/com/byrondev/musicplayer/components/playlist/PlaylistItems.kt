package com.byrondev.musicplayer.components.playlist

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.byrondev.musicplayer.R
import com.byrondev.musicplayer.components.globals.TextMedium
import com.byrondev.musicplayer.ui.theme.Pink60
import com.byrondev.musicplayer.ui.theme.Slate80
import com.byrondev.musicplayer.viewModels.MusicViewModels


@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun PlaylistItems(
    musicViewModels: MusicViewModels,
    navController: NavController,
    paddingValues: PaddingValues = PaddingValues(0.dp),
    songId: Int = 0,
) {

    val playlists = musicViewModels.playlist.collectAsState()
    val song = musicViewModels.song.collectAsState()
    val context = LocalContext.current
    val toast = Toast.makeText(context, "Se agrego correctamente", Toast.LENGTH_SHORT)

    LaunchedEffect(songId) {
        if (songId != 0) {
            musicViewModels.getSongById(id = songId)
        }
    }
    LaunchedEffect(Unit) {
        musicViewModels.clearSong()
    }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .offset(x = 0.dp, y = 10.dp)
            .padding(horizontal = 5.dp)
    ) {
        item {
            ButtonPlaylistAdd { musicViewModels.onChangeValueModal() }
            PlaylistModal(musicViewModels)
        }
        items(playlists.value) {
            PlaylistItem(it) {
                if (song.value != null) {
                    musicViewModels.insertSongToPlaylist(songId = songId, playlistId = it.id)
                    toast.show()
                    navController.popBackStack()
                } else {
                    navController.navigate("PlaylistDetailScreen/${it.id}")
                }
            }
        }
    }
}


@Composable
fun ButtonPlaylistAdd(onClick: () -> Unit) {
    Button(
        onClick = { onClick() },
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Slate80,
            contentColor = Pink60
        ),
        shape = RoundedCornerShape(10.dp),

        ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_playlist_30),
                modifier = Modifier.size(30.dp),
                contentDescription = "",
                tint = Pink60
            )
            TextMedium("Crear nueva playlist", color = Pink60)
        }
    }
}