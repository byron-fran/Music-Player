package com.byrondev.musicplayer.components.albums

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.byrondev.musicplayer.components.globals.CircleSeparation
import com.byrondev.musicplayer.components.globals.TextExtraSmall
import com.byrondev.musicplayer.data.models.Album
import com.byrondev.musicplayer.utils.dates.formatDurationText
import com.byrondev.musicplayer.utils.dates.parseDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AlbumDescription(album: Album,modifier: Modifier = Modifier) {

    Row (
        modifier = modifier.fillMaxWidth().padding(horizontal = 10.dp)
    ) {
        Column (
            verticalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier.padding(top=10.dp)
        ) {
            Row (
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextExtraSmall(text = "${album.numTracks} pistas")
                CircleSeparation()
                TextExtraSmall(text= formatDurationText(album.duration))
            }
            album.releaseDate.trim().isNotEmpty().let {
                val date = parseDate(album.releaseDate)
                if(date != null) {
                    TextExtraSmall(text="Fecha de lanzamiento: $date ")
                }
            }
            album.copyright.trim().isNotEmpty().let {
                TextExtraSmall(text= album.copyright, maxLines = 2, textAlign = TextAlign.Left)
            }
        }
    }
}