package com.byrondev.musicplayer.components.globals

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.palette.graphics.Palette
import com.byrondev.musicplayer.components.images.CoverImage
import com.byrondev.musicplayer.ui.theme.Slate80
import com.byrondev.musicplayer.ui.theme.Zinc40
import com.byrondev.musicplayer.utils.decodeBitmapWithSubsampling
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun HeaderContent(
    title: String,
    texts: List<String?>,
    height: Dp,
    scrollOffset: State<Boolean>,
    navController: NavController,
    bytesArray: List<ByteArray?>
) {
    val byteArray = bytesArray.first()
    val imageBitmap = remember { byteArray?.let {  it -> decodeBitmapWithSubsampling(it, 300, 300)  } }
    var colors by remember { mutableStateOf<List<Color>>(listOf(Color.Black, Slate80)) }

    LaunchedEffect(imageBitmap) {
        if(imageBitmap != null) {
            withContext(Dispatchers.IO) {
                val palette = Palette.from(imageBitmap).generate()
                val dominant = palette.getDominantColor(Color.Black.toArgb())
                val vibrant = palette.getVibrantColor(Slate80.toArgb())
                colors = listOf( Color(dominant),Color(vibrant),)
            }
        }
    }

    Box (
        modifier = Modifier
            .height(height).background(brush = Brush.linearGradient( colors=colors ), alpha = 0.7f).fillMaxWidth(),
        contentAlignment = Alignment.Center
    ){
        AnimateImagePlaylist(
            scrollOffset,
            byteArray,
            modifier = Modifier.align(Alignment.TopCenter).offset(x=0.dp, y = 60.dp))
        Row (
            modifier = Modifier.fillMaxWidth().offset(x=10.dp, y = 40.dp).align(Alignment.TopCenter),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
            ){
            Icon(
                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                modifier = Modifier.size(30.dp).clickable { navController.popBackStack() },
                contentDescription = "",
                tint = Color.White
            )
           AnimateTitle(title, scrollOffset)
        }
        AnimatedVisibility(
            visible = !scrollOffset.value,
            modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter).padding(bottom = 15.dp),
        ) {

            Column(
                modifier = Modifier.fillMaxWidth().align(Alignment.Center).padding(bottom = 15.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                TextLarge(title, modifier = Modifier)
                Row (
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.offset(x=0.dp, y =10.dp)
                    ){
                    texts.forEach {
                        it?.let { text ->
                            TextExtraSmall(text=text, color= Zinc40)
                            if(texts.last() != text) {
                                CircleSeparation(color = Zinc40)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AnimateTitle(text : String,   scrollOffset : State<Boolean> ) {
    AnimatedVisibility(visible =scrollOffset.value) {
        TextLarge(text)
    }
}

@Composable
fun AnimateImagePlaylist (
    scrollOffset : State<Boolean> ,
    byteArray: ByteArray?,
    modifier: Modifier = Modifier
    ) {
    AnimatedVisibility(
        modifier = modifier,
        visible = !scrollOffset.value,
        enter = scaleIn() + expandVertically (expandFrom = Alignment.CenterVertically),
        exit= scaleOut() + shrinkVertically(shrinkTowards = Alignment.CenterVertically)
    ) {
        CoverImage(byteArray, modifier = Modifier.size(230.dp))
    }
}