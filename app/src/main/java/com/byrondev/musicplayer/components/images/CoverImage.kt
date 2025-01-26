package com.byrondev.musicplayer.components.images

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.byrondev.musicplayer.R
import com.byrondev.musicplayer.utils.decodeBitmapWithSubsampling


@Composable
fun CoverImage(
    byteArray: ByteArray?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    sizeDefaultCover : Dp = 40.dp
    ) {

    val imageBitmap = byteArray?.let{ decodeBitmapWithSubsampling(byteArray, 600, 600)}

    if (imageBitmap != null) {
        Image(
            modifier = modifier.fillMaxWidth(),
            bitmap = imageBitmap.asImageBitmap(),
            contentDescription = "Cover Image",
            contentScale = contentScale

        )
        }
    else {
        Box(modifier= modifier, contentAlignment = Alignment.TopStart,) {
            Image(
                modifier = Modifier.size(sizeDefaultCover),
                painter = painterResource(id= R.drawable.baseline_music_note_600),
                contentDescription = "Cover Image default",
                )
        }
    }
}