package com.byrondev.musicplayer.components.albums

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.byrondev.musicplayer.R
import com.byrondev.musicplayer.components.globals.CircleSeparation
import com.byrondev.musicplayer.components.globals.TextExtraSmall
import com.byrondev.musicplayer.components.globals.TextMedium
import com.byrondev.musicplayer.components.images.CoverImage
import com.byrondev.musicplayer.data.models.Album


@Composable
fun AlbumCard(album: Album, navController: NavController, modifier: Modifier = Modifier) {

    Card(
        modifier = modifier
            .clickable { navController.navigate("AlbumDetail/${album.id}") }
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.Black),
        shape = RoundedCornerShape(0.dp)
    ) {
        CoverImage(album.cover, contentScale = ContentScale.Crop, modifier = Modifier.clip(RoundedCornerShape(5.dp)))
        Row(
            modifier = modifier.padding(top = 5.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = modifier.weight(1f) ) {
                TextMedium(album.title ?: "Album unknown")
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    TextExtraSmall(text=album.artist ?: "")
                    if(album.year != null) {
                        CircleSeparation()
                        TextExtraSmall(text=album.year.substringBefore("-").substringBefore(";").substringBefore("/"))
                    }
                }
            }
            album.quality?.let {
                if(it > 0) {
                    Image(
                        painter = painterResource(id = R.drawable.hi_res_logo),
                        modifier = Modifier.size(25.dp),
                        contentDescription = ""
                    )
                }
            }
        }
    }
}