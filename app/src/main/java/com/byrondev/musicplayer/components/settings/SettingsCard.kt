package com.byrondev.musicplayer.components.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.byrondev.musicplayer.R
import com.byrondev.musicplayer.ui.theme.Blue95

@Composable
fun SettingsCard(text : String, painter : Painter, onClick : () -> Unit ) {

    Card (
        modifier = Modifier.fillMaxWidth().height(50.dp).clickable { onClick() },
        shape = RoundedCornerShape(5.dp),
        colors =  CardDefaults.cardColors(
            containerColor =  Blue95,
            contentColor = Color.White
        ),

        ) {
        Row (
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Icon(
                painter ,
                contentDescription =  "Icon $text"
            )
            Text(text)
        }

    }
}