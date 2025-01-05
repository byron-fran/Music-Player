package com.byrondev.musicplayer.components.images

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.byrondev.musicplayer.R
import com.byrondev.musicplayer.utils.decodeBitmapWithSubsampling


@Composable
fun CoverImage(byteArray: ByteArray?, modifier: Modifier = Modifier, contentScale: ContentScale = ContentScale.Crop) {

    val imageBitmap = byteArray?.let{ decodeBitmapWithSubsampling(byteArray, 600, 600)}

    if (imageBitmap != null) {
            Image(

                modifier = modifier.clip(RoundedCornerShape(5.dp)).fillMaxWidth(),
                bitmap = imageBitmap.asImageBitmap(),
                contentDescription = "Cover Image",
                contentScale = contentScale

            )
        }
    else {
        Box(
            modifier= modifier,
            contentAlignment = Alignment.Center,

        ) {
            Card (
                modifier = Modifier.fillMaxSize(),
                shape = RoundedCornerShape(5.dp),
                colors = CardDefaults.cardColors( /* Todo add color container */ contentColor =  Color.White)
                ) {}
            Image(
                painter = painterResource(id= R.drawable.baseline_music_note_600),
                modifier = Modifier.size(200.dp),
                contentDescription = "Cover Image default",

                )
        }
    }
}