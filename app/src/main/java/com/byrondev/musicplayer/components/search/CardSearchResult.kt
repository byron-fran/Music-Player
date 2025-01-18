package com.byrondev.musicplayer.components.search

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.byrondev.musicplayer.components.globals.TextExtraSmall
import com.byrondev.musicplayer.components.globals.TextMedium
import com.byrondev.musicplayer.components.images.CoverImage
import com.byrondev.musicplayer.components.songs.SongCard
import com.byrondev.musicplayer.data.dao.SearchResult
import com.byrondev.musicplayer.data.models.Song
import com.byrondev.musicplayer.ui.theme.textWhite15

@Composable
fun CardAlbumSearch  ( result : SearchResult, onClick: () -> Unit) {

    Row (
        modifier = Modifier.height(55.dp).clickable {onClick()},
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),

    ) {
        CoverImage(result.cover, modifier = Modifier.width(50.dp).height(50.dp))

        Column (
            verticalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier.fillMaxWidth()

            ) {
            TextMedium(result.name,color = Color.White)
            result.artist?.let {
                TextExtraSmall(text="Album by $it")
            }
        }
    }

}

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun CardSongResult(result: SearchResult, navController : NavController,onClick : () -> Unit ) {

    Row (
        modifier = Modifier.clickable {  },
        verticalAlignment = Alignment.CenterVertically
        ){
        CoverImage(result.cover, modifier = Modifier.width(50.dp).height(50.dp))
        SongCard(
            song = Song(
                id = result.id,
                title = result.name,
                album = result.album,
                artist = result.artist,
                cover = result.cover,
                uri = result.uri ?: "",
                bitRate =  result.bitrate
            ),
            showTrackNumber = false,
            navController,
        ) { onClick() }
    }

}

@Composable
fun CardArtistResult( result: SearchResult, onClick: () -> Unit) {
    Row  (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.clickable { onClick() }
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
            TextMedium(result.name)
            TextExtraSmall(text="Artist")
        }
    }
}