package com.byrondev.musicplayer.components.menu

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.byrondev.musicplayer.ui.theme.textWhite15

@Composable
fun MenuItem(icon: Painter, size: Dp, title : String ,  ) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .padding(horizontal = 5.dp, vertical = 7.dp )
            .clickable { /* !todo add events */ }
    ) {
        Icon(
            painter =  icon,
            contentDescription = "",
            tint = Color.White,
            modifier = Modifier.size(size)
        )
        Text(title, style = textWhite15)
    }

}