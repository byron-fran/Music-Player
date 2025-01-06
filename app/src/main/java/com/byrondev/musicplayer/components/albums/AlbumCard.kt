package com.byrondev.musicplayer.components.albums

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.byrondev.musicplayer.R
import com.byrondev.musicplayer.data.models.Album
import com.byrondev.musicplayer.ui.theme.Gray20


fun byteArrayToBitmap(byteArray: ByteArray): Bitmap {
    return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
}

@Composable
fun AlbumCard(album: Album, navController: NavController) {

    val albumArtBitmap = album.cover?.let { byteArrayToBitmap(it) }
    // Todo update how get album Cover
    Card(
        modifier = Modifier
            .clickable { navController.navigate("AlbumDetail/${album.id}") }
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        shape = RoundedCornerShape(5.dp)
    ) {

        if (albumArtBitmap != null) {
            Image(
                bitmap = albumArtBitmap.asImageBitmap(), contentDescription = "",
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        } else {
            Image(
                imageVector = Icons.Default.Email, contentDescription = "Album default image",
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )

        }
        Row(
            modifier = Modifier
                .padding(top = 5.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier) {

                Text(
                    album.title ?: "Unknown Album",
                    color = Color.White,
                    modifier = Modifier.width(140.dp),
                    maxLines = 1,
                    minLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    album.artist ?: "",
                    color = Gray20,
                    maxLines = 1,
                    minLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 17.sp
                )
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
