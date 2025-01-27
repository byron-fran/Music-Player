package com.byrondev.musicplayer.components.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.byrondev.musicplayer.components.globals.TextMedium
import com.byrondev.musicplayer.ui.theme.Pink60
import com.byrondev.musicplayer.ui.theme.Slate80


@Composable
fun CardHome(onClick: () -> Unit, title : String, iconName : Painter ) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .background(Slate80, shape = RoundedCornerShape(10.dp))
            .height(130.dp)
            .clickable { onClick() }
        ,
        contentAlignment = Alignment.Center,

    ) {
        Card(
            modifier = Modifier.fillMaxSize(),
            colors = CardDefaults.cardColors(containerColor = Color.Transparent)
        ) {}
        Row  (
            verticalAlignment = Alignment.CenterVertically,
        ){

           TextMedium(title, color = Pink60)
            Icon(
                painter = iconName,
                modifier = Modifier.size(30.dp),
                contentDescription = "on click icon",
                tint = Pink60,
            )
        }
    }

}