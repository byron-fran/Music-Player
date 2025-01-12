package com.byrondev.musicplayer.components.topbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.byrondev.musicplayer.components.globals.TextLarge


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenterTopAppBar(
    title : String,
    iconName : ImageVector = Icons.AutoMirrored.Default.ArrowBack,
    color : Color = Color.Black,
    onNavigate: () -> Unit,
    ) {

    CenterAlignedTopAppBar(
        title = { TextLarge(title) },
        navigationIcon = {
            Icon(
                imageVector = iconName,
                modifier = Modifier.size(30.dp) .clickable { onNavigate() },
                contentDescription = "Icon back",

                ) },

        actions =  {},
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = color,
            actionIconContentColor = Color.White,
            navigationIconContentColor = Color.White
        )
    )
}