package com.byrondev.musicplayer.components.globals

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun  TopAppBarLeft (
    title: String,
    scrollOffset: State<Boolean>,
    modifier: Modifier = Modifier,
    color: Color = Color.Transparent,
    onClick: () -> Unit

    ) {
    TopAppBar(
        modifier = modifier,
        title = {
            AnimatedVisibility(visible = scrollOffset.value) {
                TextLarge(title)
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = color),
        navigationIcon = {
            Icon(
                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                contentDescription = "",
                modifier = Modifier.size(30.dp).clickable { onClick() },
                tint = Color.White
            )
        },
        actions =  {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "",
                modifier = Modifier.size(25.dp).clickable { /* Todo add event */ },
                tint = Color.White
            )
        }
    )
}