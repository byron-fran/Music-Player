package com.byrondev.musicplayer.components.globals

import android.os.Build
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.byrondev.musicplayer.ui.theme.Gray
import com.byrondev.musicplayer.utils.bitmap.getCoverArt
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun LazyImageCover(
    uri : String?,
    modifier: Modifier = Modifier,
    alpha : Float = 1f,
    content : @Composable () -> Unit
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
                contentScale = ContentScale.Crop,
                alpha = alpha
            )
        }
    } else {
        content()
    }

}


@Composable
fun SmallImage (
    @DrawableRes id : Int,
    modifier: Modifier = Modifier,
    tint : Color = Gray,
) {
    Image(
        modifier = modifier.size(40.dp),
        painter = painterResource(id),
        contentDescription = "Small image",
        colorFilter = ColorFilter.tint(tint)
    )
}

@Composable
fun LargeImage (
    @DrawableRes id : Int,
    modifier: Modifier = Modifier,
    tint : Color = Gray,
) {
    Image(

        modifier = modifier.size(150.dp),
        painter = painterResource(id),
        contentDescription = "Medium image",
        colorFilter = ColorFilter.tint(tint)
    )
}

@Composable
fun ImageDefault(
    @DrawableRes id: Int,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id=id),
        contentDescription = "Album Art",
        modifier = modifier.clip(RoundedCornerShape(5.dp)),
        contentScale = ContentScale.Crop
    )
}