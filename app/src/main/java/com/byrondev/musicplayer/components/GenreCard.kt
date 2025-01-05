package com.byrondev.musicplayer.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.byrondev.musicplayer.R
import com.byrondev.musicplayer.ui.theme.textWhite15
import com.byrondev.musicplayer.utils.randomColorBrush


@Composable
fun GenreCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(
                brush = randomColorBrush(),
                shape = RoundedCornerShape(5.dp),
                alpha =  0.5f
            )
    ) {
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth().height(100.dp)
        ) {
            Image(
                painter = painterResource(id= R.drawable.baseline_sound_90),
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )
            Text("Rap/Hip-hop", style = textWhite15, maxLines = 1, overflow = TextOverflow.Ellipsis )
        }
    }
}