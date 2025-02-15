package com.byrondev.musicplayer.components.images

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import com.byrondev.musicplayer.ui.theme.Slate70
import com.byrondev.musicplayer.utils.decodeBitmapWithSubsampling
import com.byrondev.musicplayer.utils.produceBlurredBitmap

@Composable
fun BackgroundImage(byteArray : ByteArray?, modifier: Modifier = Modifier, alpha : Float = 0.5f) {

    val imageBitmap = remember { byteArray?.let { decodeBitmapWithSubsampling(it, 300, 300)  } }
    var blurredBitmap by remember { mutableStateOf<Bitmap?>(null) }
    val context = LocalContext.current

    LaunchedEffect (blurredBitmap) {
        blurredBitmap = produceBlurredBitmap(imageBitmap, context )
    }

    if (blurredBitmap != null) {
        blurredBitmap?.asImageBitmap()?.let {
            Image(
                modifier = modifier,
                bitmap = it,
                alpha = alpha,
                contentScale = ContentScale.Crop,
                contentDescription = "",

            )
        }
    }
    else {
        Box (modifier.background(Slate70)) {}
    }
}