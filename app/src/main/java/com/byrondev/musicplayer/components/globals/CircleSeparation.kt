package com.byrondev.musicplayer.components.globals

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.byrondev.musicplayer.R
import com.byrondev.musicplayer.ui.theme.Zinc40

@Composable
fun CircleSeparation  (){
    Icon(
        painter = painterResource(id = R.drawable.baseline_circle_30),
        tint =  Zinc40,
        modifier = Modifier.size(6.dp),
        contentDescription = "Icon separation",
    )
}