package com.byrondev.musicplayer.components.albums

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.byrondev.musicplayer.components.globals.CircleSeparation
import com.byrondev.musicplayer.components.globals.TextExtraSmall
import com.byrondev.musicplayer.components.globals.TextLarge
import com.byrondev.musicplayer.components.images.BackgroundImage
import com.byrondev.musicplayer.components.images.CoverImage
import com.byrondev.musicplayer.data.models.Album
import com.byrondev.musicplayer.utils.fonts.formatYear

@Composable
fun AlbumHeader (
    album: Album,
    scrollOffset : State<Boolean>,
    navController: NavController,
    modifier: Modifier = Modifier
    ) {

    Box(
        modifier = modifier.background(Color.Black)) {
        BackgroundImage(album.cover, modifier = Modifier.fillMaxSize().background(Color.Black))
        Column  (horizontalAlignment = Alignment.CenterHorizontally,){

            Row (
                modifier = Modifier.fillMaxWidth().background(Color.Transparent).offset(x=0.dp, y = 40.dp),
                verticalAlignment = Alignment.Top
            ){
                Icon(
                    imageVector =  Icons.AutoMirrored.Default.ArrowBack,
                    modifier = Modifier.size(30.dp).clickable {  navController.popBackStack()},
                    contentDescription =  "",
                    tint = Color.White,
                )

               Box (
                   modifier = Modifier.fillMaxWidth().padding(end = 30.dp),
                   contentAlignment = Alignment.Center
               ){
                   TextLarge(album.title ?: "Unknown album")
               }
            }

            AnimatedVisibility(!scrollOffset.value) {
                Column (horizontalAlignment = Alignment.CenterHorizontally) {
                    CoverImage(album.cover, modifier = Modifier.width(200.dp).height(200.dp).offset(x= 0.dp, y = 45.dp))
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
        TextExtraSmall(text = album?.artist ?: "Unknown artist")
        album?.year?.let {
            CircleSeparation()
            TextExtraSmall(text=it.formatYear())
        }
        album?.genres?.let {
            CircleSeparation()
            TextExtraSmall(text=it)
        }

    }

}