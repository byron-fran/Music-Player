package com.byrondev.musicplayer.components.images

import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.byrondev.musicplayer.R
import com.byrondev.musicplayer.utils.decodeBitmapWithSubsampling


@Composable
fun CoverImage(
    byteArray: ByteArray?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
) {

    val imageBitmap = byteArray?.let { decodeBitmapWithSubsampling(byteArray, 600, 600) }

    if (imageBitmap != null) {
        Image(
            modifier = modifier
                .clip(shape = RoundedCornerShape(5.dp)),
            bitmap = imageBitmap.asImageBitmap(),
            contentDescription = "Cover Image",
            contentScale = contentScale

        )
    } else {
        Image(
            modifier = modifier
                .clip(shape = RoundedCornerShape(5.dp)),
            painter = painterResource(id = R.drawable.image_music_default),
            contentDescription = "Cover Image default",
        )

    }
}