package com.byrondev.musicplayer.components.globals

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.byrondev.musicplayer.components.images.CoverImage

@Composable
fun HeaderContent(
    title: String,
    texts: List<String?>,
    bytesArray: List<ByteArray?>,
    navController: NavController,
) {
    val byteArray = bytesArray.first()
    val newTexts = texts.filter { it?.length!! > 0 }

    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)

    ) {
        Column (horizontalAlignment = Alignment.CenterHorizontally){
            CoverImage(byteArray)
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier.padding(top = 10.dp)
            ) {
                TextLarge(title)
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
            }
        }
    }
}