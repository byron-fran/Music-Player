package com.byrondev.musicplayer.components.texts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun TextRowSeparation(
    text1 : String,
    text2 : String,
    navController: NavController,
    path : String
    ) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(top=40.dp, start = 20.dp, end = 20.dp, bottom = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){

        Text(text1, color= Color.White, fontWeight = FontWeight.Bold, fontSize = 25.sp )
        Row (modifier = Modifier, verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.SpaceBetween) {

            Text(text2, /*Todo add color text */ fontWeight = FontWeight.Normal, fontSize = 20.sp,
                 modifier = Modifier.clickable { navController.navigate(path)
                 }
            )
            Spacer(modifier = Modifier.width(5.dp))
            Icon(
                imageVector = Icons.AutoMirrored.Default.ArrowForward,
                modifier = Modifier.size(17.dp),
                contentDescription =  "Icon To view more",
                 /*Todo add color tint */
            )
        }
    }

}