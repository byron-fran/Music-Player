package com.byrondev.musicplayer.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.byrondev.musicplayer.ui.theme.Gray10
import com.byrondev.musicplayer.ui.theme.Pink60
import com.byrondev.musicplayer.ui.theme.Yellow50
import com.byrondev.musicplayer.viewModels.MusicViewModels


@Composable
fun FavoritesScreen(navController: NavController, musicViewModels: MusicViewModels) {
    Row (modifier = Modifier.fillMaxWidth()){
        Row (modifier = Modifier.background(color = Pink60).weight(2f).fillMaxWidth().width(200.dp)) {
            Text("Hola")
        }

        Row (modifier = Modifier.background(color = Gray10).weight(2f).width(200.dp)) {
            Text("Hola")
        }

        Row (modifier = Modifier.background(color = Yellow50).weight(2f).fillMaxWidth().width(200.dp)) {
            Text("Hola")
        }
    }
}