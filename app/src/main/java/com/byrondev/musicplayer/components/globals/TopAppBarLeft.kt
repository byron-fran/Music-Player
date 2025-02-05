package com.byrondev.musicplayer.components.globals

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TopAppBarLeft(
    title: String,
    scrollOffset: State<Boolean>,
    modifier: Modifier = Modifier,
    color: Color = Color.Transparent,
    onClick: () -> Unit,
    ) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color)
            .padding(bottom = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                contentDescription = "",
                modifier = Modifier
                    .size(25.dp)
                    .clickable { onClick() },
                tint = Color.White
            )
            AnimatedVisibility(visible = scrollOffset.value) {
                TextLarge(title)
            }
        }
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = "",
            modifier = Modifier
                .size(25.dp)
                .clickable { /* Todo add event */ },
            tint = Color.White
        )
    }
}