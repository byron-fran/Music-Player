package com.byrondev.musicplayer.components.globals

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun HeaderContent(
    title: String,
    texts: List<String?>,
    coverImage : @Composable () -> Unit,
    content : @Composable () -> Unit
) {

    val newTexts = texts.filter { it?.length!! > 0 }

    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .fillMaxWidth()
            .height(310.dp)

    ) {
        Column (horizontalAlignment = Alignment.CenterHorizontally){

            coverImage()
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier.padding(top = 10.dp)
            ) {
                TextLarge(title, modifier = Modifier.widthIn(max = 350.dp), maxLines = 2, color = Color.White)
                Row(
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    newTexts.forEach { t ->
                        TextExtraSmall(text = "$t")
                        if (texts.last() != t) {
                            CircleSeparation()
                        }
                    }
                }
                content()
            }
        }
    }
}