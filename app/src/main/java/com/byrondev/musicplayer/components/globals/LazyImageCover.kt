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
import androidx.compose.ui.unit.dp
import com.byrondev.musicplayer.viewModels.PlayerViewModels
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun LazyImageCover(
    playerViewModels: PlayerViewModels,
    uri : String,
    imageDefault : Painter,
    modifier: Modifier = Modifier
    ) {

    var albumArt by remember { mutableStateOf<ImageBitmap?>(null) }

    LaunchedEffect(uri) {
        withContext(Dispatchers.IO) {
            val bitmap = playerViewModels.getCoverArt(uri)
            albumArt = bitmap?.asImageBitmap()
        }
    }

    if (albumArt != null) {
        Image(
            bitmap = albumArt!!,
            contentDescription = "Album Art",
            modifier = modifier.clip(RoundedCornerShape(5.dp))
        )
    } else {
        Image(
            painter = imageDefault,
            contentDescription = "Placeholder",
            modifier = modifier.clip(RoundedCornerShape(5.dp))
        )
    }

}

