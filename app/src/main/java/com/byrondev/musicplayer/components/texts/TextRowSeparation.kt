package com.byrondev.musicplayer.components.texts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.byrondev.musicplayer.R
import com.byrondev.musicplayer.components.globals.IconSmall
import com.byrondev.musicplayer.components.globals.TextLarge
import com.byrondev.musicplayer.components.globals.TextMedium


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
        TextLarge(text1,color= Color.White )
        if(text2.trim().isNotEmpty()) {
            Row (
                modifier = Modifier.clickable { onClick() },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                TextMedium(text2)
                IconSmall(R.drawable.arrow_right_alt_30) { }
            }
        }
    }

}