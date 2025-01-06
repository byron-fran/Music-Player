package com.byrondev.musicplayer.components.albums

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.byrondev.musicplayer.components.images.BackgroundImage
import com.byrondev.musicplayer.components.images.CoverImage
import com.byrondev.musicplayer.ui.theme.textWhite15

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun AlbumAppTopBar(cover :  ByteArray?, albumName : String?) {

    Box(
        modifier = Modifier.fillMaxWidth().height(350.dp).background(Color.Transparent),

    ){

        BackgroundImage(cover, modifier = Modifier.fillMaxSize())
        Row (
            modifier = Modifier.fillMaxWidth().background(Color.Transparent).height(70.dp),
            verticalAlignment = Alignment.Bottom
        ){
            Icon(
                imageVector =  Icons.AutoMirrored.Default.ArrowBack,
                contentDescription =  "",
                tint = Color.White,
                modifier = Modifier.size(30.dp)
            )
            Text(
                albumName ?: "Unknown Album",
                modifier = Modifier.fillMaxWidth().padding(end = 30.dp),
                color= Color.White,
                textAlign = TextAlign.Center,
                style = textWhite15,
            )
        }

        CoverImage(
            cover,
            modifier = Modifier
                .width(210.dp)
                .height(220.dp)
                .align(Alignment.Center)
                .offset(x =0.dp, y=25.dp),

        )
    }

}


