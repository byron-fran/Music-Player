package com.byrondev.musicplayer.components.songs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController

@Composable
fun SongPlayFloatingButton(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Red)
            .height(60.dp)
            .padding(bottom = 70.dp)
            .clickable {  navController.navigate("SongDetailPlaying") }
            .zIndex(1f)
        ,

    ) {

        Text("Hello wolrd", color = Color.White)

    }
}


@Composable
fun Barra() {


}