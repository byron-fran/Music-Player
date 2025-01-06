package com.byrondev.musicplayer.components.search

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.byrondev.musicplayer.components.images.CoverImage
import com.byrondev.musicplayer.components.songs.SongCard
import com.byrondev.musicplayer.data.dao.SearchResult
import com.byrondev.musicplayer.data.models.Song
import com.byrondev.musicplayer.ui.theme.textDarkGray13
import com.byrondev.musicplayer.ui.theme.textWhite15
import com.byrondev.musicplayer.viewModels.PlayerViewModels

@Composable
fun CardAlbumSearch  (navController: NavController, result : SearchResult) {

    Row (
        modifier = Modifier.height(55.dp).clickable {navController.navigate("AlbumDetail/${result.id}") },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        CoverImage(result.cover, modifier = Modifier.width(50.dp).height(50.dp))
        Column (
            verticalArrangement = Arrangement.spacedBy(5.dp),

            ) {
            Text(result.name, style = textWhite15)
            result.artist?.let {
                Text("Album by $it", style = textDarkGray13)
            }
        }
    }

}

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun CardSongResult(result: SearchResult, playerViewModels: PlayerViewModels) {

    Row {
        CoverImage(result.cover, modifier = Modifier.width(50.dp).height(50.dp))
        SongCard(
            song = Song(
                id = result.id,
                title = result.name,
                album = result.album,
                artist = result.artist,
                cover = result.cover,
                uri = result.uri ?: "",
                audioBitDepth =  result.audioBitDepth
            ),
            showTrackNumber = false
        ) {
            // Todo add onClick
        }
    }

}

@Composable
fun CardArtistResult( result: SearchResult) {
    Row  (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Icon(
            imageVector =  Icons.Default.Person,
            modifier = Modifier.width(50.dp).height(50.dp),
            contentDescription =  "",
            tint = Color.White
        )
        Column (
            verticalArrangement = Arrangement.spacedBy(5.dp),
        ) {
            Text(result.name, style = textWhite15)
            Text("Artist", style = textDarkGray13)
        }
    }
}