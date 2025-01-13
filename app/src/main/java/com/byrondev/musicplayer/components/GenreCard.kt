package com.byrondev.musicplayer.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.byrondev.musicplayer.R
import com.byrondev.musicplayer.components.globals.TextMedium
import com.byrondev.musicplayer.data.models.Genre
import com.byrondev.musicplayer.ui.theme.Slate80


@Composable
fun GenreCard(genre: Genre, navController: NavController) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(Slate80, shape = RoundedCornerShape(5.dp))
            .clickable { navController.navigate("SongsByGenreScreen/${genre.id}") }
    ) {
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth().height(100.dp)
        ) {

            Image(
                painter = painterResource(id= R.drawable.baseline_sound_90),
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )
            TextMedium(genre.name)
        }
    }
}