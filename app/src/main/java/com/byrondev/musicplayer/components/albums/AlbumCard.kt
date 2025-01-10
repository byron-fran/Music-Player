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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.byrondev.musicplayer.R
import com.byrondev.musicplayer.components.globals.CircleSeparation
import com.byrondev.musicplayer.components.globals.TextExtraSmall
import com.byrondev.musicplayer.components.images.CoverImage
import com.byrondev.musicplayer.data.models.Album


@Composable
fun AlbumCard(album: Album, navController: NavController) {

    Card(
        modifier = Modifier
            .clickable { navController.navigate("AlbumDetail/${album.id}") }
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        shape = RoundedCornerShape(5.dp)
    ) {

        CoverImage(album.cover, contentScale = ContentScale.Crop)
        Row(
            modifier = Modifier.padding(top = 5.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {

                Text(album.title ?: "Unknown Album",
                    color = Color.White,
                    maxLines = 1,
                    minLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    TextExtraSmall(text=album.artist ?: "")
                    if(album.year != null) {
                        CircleSeparation()
                        TextExtraSmall(text=album.year.substringBefore("-"))
                    }
                }
            }

            if(album.quality == 3) {
                Image(
                    painter = painterResource(id = R.drawable.hi_res_logo),
                    contentDescription = "",
                    modifier = Modifier.size(35.dp)
                )
            }

        }

    }

}
