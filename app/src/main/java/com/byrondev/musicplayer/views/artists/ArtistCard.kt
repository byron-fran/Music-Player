package com.byrondev.musicplayer.views.artists

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.byrondev.musicplayer.R
import com.byrondev.musicplayer.components.globals.CircleSeparation
import com.byrondev.musicplayer.components.globals.SmallImage
import com.byrondev.musicplayer.components.globals.TextExtraSmall
import com.byrondev.musicplayer.components.globals.TextMedium
import com.byrondev.musicplayer.data.models.Artist
import com.byrondev.musicplayer.ui.theme.Slate80


@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun ArtistCard(artist: Artist, onClick: () -> Unit) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .clickable { onClick() }
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .width(50.dp)
                .height(50.dp)
                .background(Slate80, shape = RoundedCornerShape(5.dp)),
            contentAlignment = Alignment.Center

        ) {
            SmallImage(R.drawable.artist_50)
        }
        ArtistCardInfo(artist)
    }
}

@Composable
fun ArtistCardInfo(artist: Artist, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.height(40.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        TextMedium(artist.name ?: stringResource(id=R.string.unknown_artist), color= Color.White)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            artist.numOfSongs?.let {
                if (it > 1) TextExtraSmall(text = "${artist.numOfSongs} " + stringResource(id=R.string.library_songs))
                else TextExtraSmall(text = "${artist.numOfSongs} " + stringResource(R.string.song))
            }
            artist.numOfAlbums?.let {
                CircleSeparation()
                if (it > 1) TextExtraSmall(text = "$it " + stringResource(R.string.library_albums))
                else TextExtraSmall(text = "$it " + stringResource(R.string.album))
            }
        }
    }
}