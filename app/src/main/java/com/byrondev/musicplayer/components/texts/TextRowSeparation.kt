package com.byrondev.musicplayer.components.texts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.byrondev.musicplayer.components.globals.TextLarge
import com.byrondev.musicplayer.components.globals.TextMedium
import com.byrondev.musicplayer.ui.theme.Zinc40


@Composable
fun TextRowSeparation(
    text1 : String,
    text2 : String,
    modifier: Modifier = Modifier,
    onClick : () -> Unit
    ) {
    Row (
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        TextLarge(text1)
        Row (
            modifier = Modifier, verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            TextMedium(text2, color = Zinc40, modifier = Modifier.clickable { onClick() },)
            Icon(
                imageVector = Icons.AutoMirrored.Default.ArrowForward,
                modifier = Modifier.size(30.dp),
                contentDescription =  "Icon To view more",
                tint = Zinc40
            )
        }
    }

}