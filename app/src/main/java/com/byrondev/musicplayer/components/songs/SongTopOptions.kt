package com.byrondev.musicplayer.components.songs

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.byrondev.musicplayer.R
import com.byrondev.musicplayer.components.globals.IconSmall
import com.byrondev.musicplayer.components.globals.TextLarge
import com.byrondev.musicplayer.components.menu.MenuItem
import com.byrondev.musicplayer.data.models.Song
import com.byrondev.musicplayer.layout.SheetModalLayout
import com.byrondev.musicplayer.viewModels.MusicViewModels
import com.byrondev.musicplayer.viewModels.PlayerViewModels

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun SongTopOptions(
    playerViewModels: PlayerViewModels,
    musicViewModels: MusicViewModels,
    navController: NavController,
    song: Song,
    showModalLayout: MutableState<Boolean>,
    onClick: () -> Unit,
) {

    Row(
        modifier = Modifier.fillMaxWidth().padding(top = 50.dp, bottom = 25.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            imageVector = Icons.Default.KeyboardArrowDown, contentDescription = "Back",
            modifier = Modifier.size(25.dp).clickable { onClick() },
            tint = Color.White
        )
        TextLarge(
            text = song.artist ?: stringResource(id=R.string.unknown_artist),
            color = Color.White,
            modifier = Modifier
                .widthIn(max = 250.dp)
                .clickable {
                    navController.navigate("ArtistDetail/${song.artistId}")
                    onClick()
                }
        )
        IconSmall(R.drawable.more_vert_30, tint = Color.White,) {
            showModalLayout.value = true
        }
        SheetModalLayout(showModalLayout) {
            SongOptionsItems(
                playerViewModels,
                musicViewModels,
                navController,
                song,
                showModalLayout
            ) {
                MenuItem(R.drawable.baseline_cd_30, title = R.string.go_to_album) {
                    navController.navigate("AlbumDetail/${song.albumId}")
                    showModalLayout.value = false
                    playerViewModels.onChangeModalState(false)
                }
            }
        }
    }
}