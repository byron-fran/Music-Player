package com.byrondev.musicplayer.components.albums

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.byrondev.musicplayer.components.globals.CircleSeparation
import com.byrondev.musicplayer.components.images.BackgroundImage
import com.byrondev.musicplayer.components.images.CoverImage
import com.byrondev.musicplayer.data.models.Album
import com.byrondev.musicplayer.ui.theme.Zinc40
import com.byrondev.musicplayer.ui.theme.textDarkGray13
import com.byrondev.musicplayer.ui.theme.textWhite15

@Composable
fun AlbumHeader ( album: Album,animatedHeight : Dp, scrollOffset : State<Boolean>) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(animatedHeight),

        ) {
        BackgroundImage(album.cover, modifier = Modifier.fillMaxSize())
        Column  (horizontalAlignment = Alignment.CenterHorizontally,){

            Row (
                modifier = Modifier.fillMaxWidth().background(Color.Transparent).offset(x=0.dp, y = 30.dp),
                verticalAlignment = Alignment.Bottom
            ){
                Icon(
                    imageVector =  Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription =  "",
                    tint = Color.White,
                    modifier = Modifier.size(30.dp)
                )
                Text(
                    album.title ?: "Unknown Album",
                    modifier = Modifier.fillMaxWidth().padding(end = 35.dp),
                    color= Color.White,
                    textAlign = TextAlign.Center,
                    style = textWhite15,
                )
            }

            AnimatedVisibility(!scrollOffset.value) {
                Column {
                    CoverImage(album.cover, modifier = Modifier.width(200.dp).offset(x= 0.dp, y = 45.dp))
                    AlbumInfo(album)
                }
            }
        }

    }
}


@Composable
fun AlbumInfo (album: Album?) {
    Row(
        modifier = Modifier.offset(x =0.dp, y = 70.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)

    ) {
        Text(album?.artist ?: "Unknown artist", style = textDarkGray13, color = Zinc40)
        CircleSeparation()
        Text(album?.year?.substringBefore("-") ?: "", style = textDarkGray13, color = Zinc40)
        CircleSeparation()
        Text(album?.genres ?: "", style = textDarkGray13, color = Zinc40)
    }
}