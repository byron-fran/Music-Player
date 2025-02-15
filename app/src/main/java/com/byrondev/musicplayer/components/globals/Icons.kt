package com.byrondev.musicplayer.components.globals

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.byrondev.musicplayer.ui.theme.Gray

@Composable
fun IconSmall(
    @DrawableRes id: Int,
    modifier: Modifier = Modifier,
    tint: Color = Gray,
    onClick: () -> Unit
) {

    Icon(
        painter = painterResource(id = id),
        modifier = modifier.size(25.dp).clickable {onClick()  },
        tint = tint,
        contentDescription = "Icon Small"
    )
}