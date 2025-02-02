package com.byrondev.musicplayer.components.globals

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.byrondev.musicplayer.utils.bitmap.getCoverArt
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun LazyImageCover(
    uri : String?,
    imageDefault : Painter,
    modifier: Modifier = Modifier,
  ) {

    var albumArt by remember { mutableStateOf<ImageBitmap?>(null) }
    val context = LocalContext.current

    LaunchedEffect(uri) {
        withContext(Dispatchers.IO) {
            if(uri?.trim()?.isNotEmpty() == true) {
                val bitmap =  getCoverArt(uri, context)
                albumArt = bitmap?.asImageBitmap()
            }
        }
    }

    if (albumArt != null) {
        albumArt?.let {
            Image(
                bitmap = albumArt!!,
                contentDescription = "Album Art",
                modifier = modifier.clip(RoundedCornerShape(5.dp)),
                contentScale = ContentScale.Crop
            )
        }
    } else {
        Image(
            painter = imageDefault,
            contentDescription = "Album Art",
            modifier = modifier.clip(RoundedCornerShape(5.dp)),
            contentScale = ContentScale.Crop
        )
    }

}

