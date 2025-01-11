package com.byrondev.musicplayer.components.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.byrondev.musicplayer.components.globals.TextMedium
import com.byrondev.musicplayer.ui.theme.Slate70


@Composable
fun CardHome(onClick: () -> Unit, title : String, iconName : Painter ) {

    Box(
        modifier = Modifier.fillMaxWidth().background(Slate70).height(170.dp),
        contentAlignment = Alignment.Center,

    ) {
        Card(
            modifier = Modifier.fillMaxSize().padding(10.dp),
            shape = RoundedCornerShape(5.dp),
            colors = CardDefaults.cardColors(
                contentColor =  Color.White,
                containerColor = Color.Transparent
            )
        ) {}
        Row  (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable { onClick() }
        ){

           TextMedium(title)
            Icon(
                painter = iconName,
                modifier = Modifier.size(30.dp),
                contentDescription = "on click icon",
                tint = Color.White,
            )

        }
    }
    Spacer(modifier = Modifier.height(15.dp))

}