package com.byrondev.musicplayer.components.songs

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.byrondev.musicplayer.R
import com.byrondev.musicplayer.components.globals.CircleSeparation
import com.byrondev.musicplayer.components.globals.TextExtraSmall
import com.byrondev.musicplayer.components.globals.TextMedium
import com.byrondev.musicplayer.components.images.CoverImage
import com.byrondev.musicplayer.components.menu.MenuItem
import com.byrondev.musicplayer.data.models.Song
import com.byrondev.musicplayer.utils.bitmap.getByteArray
import com.byrondev.musicplayer.viewModels.MusicViewModels
import com.byrondev.musicplayer.viewModels.PlayerViewModels

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun SongOptionsItems(
    playerViewModels: PlayerViewModels,
    musicViewModels: MusicViewModels,
    navController: NavController,
    song: Song,
    showModalLayout: MutableState<Boolean>,
    moreContentItems: @Composable () -> Unit,
) {
    val context = LocalContext.current
    val byteArray = getByteArray(song.uri, context)
    val toast = Toast.makeText(context, "", Toast.LENGTH_SHORT)

    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CoverImage(byteArray, modifier = Modifier.size(50.dp))
        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp),
        ) {

            TextMedium(song.title ?: "", color = Color.White)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                TextExtraSmall(text = song.artist ?: "")
                CircleSeparation()
                TextExtraSmall(text = song.year ?: "")
            }
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        moreContentItems()

        MenuItem(R.drawable.music_note_add_30, title = R.string.add_playlist) {

            navController.navigate("PlaylistScreen/${song.id}")
        }
        MenuItem(R.drawable.artist_50, title = R.string.go_to_artist) {

        }

        if (song.isFavorite) {
            MenuItem(R.drawable.favorite_filled, title = R.string.remove_favorite) {
                toast.setText(R.string.remove_success)
                toast.show()
                musicViewModels.deleteFavoriteSong(song.id)
                showModalLayout.value = false
            }
        } else {
            MenuItem(R.drawable.favorite_outline, title = R.string.add_favorite) {
                toast.setText(R.string.add_success)
                toast.show()
                musicViewModels.insertFavoritesSong(listOf(song))
                showModalLayout.value = false
            }
        }
        MenuItem(R.drawable.remove_30, title = R.string.song_delete) {
            /* Todo add event*/
        }

        MenuItem(R.drawable.baseline_info_30, title = R.string.song_detail) {
            /* Todo add event*/
        }
    }
}