package com.byrondev.musicplayer.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.byrondev.musicplayer.R
import com.byrondev.musicplayer.components.globals.SmallImage
import com.byrondev.musicplayer.components.globals.TextExtraSmall
import com.byrondev.musicplayer.components.globals.TextMedium
import com.byrondev.musicplayer.data.models.Genre


@Composable
fun GenreCard(genre: Genre, navController: NavController) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors =
                    listOf(Color(genre.color1), Color(genre.color2))
                ),
                shape = RoundedCornerShape(5.dp),
                alpha = 0.6f
            )
            .clickable { navController.navigate("SongsByGenreScreen/${genre.id}") }
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        ) {
            SmallImage(R.drawable.genres_50,tint =Color.White)
            genre.name?.let {
                if (it.trim().isNotEmpty()) {
                    TextMedium(genre.name, color = Color.White)
                } else {
                    TextMedium(stringResource(R.string.unknown_gender), color = Color.White)
                }
            }
            TextExtraSmall(text="${genre.numOfSongs} " + if(genre.numOfSongs > 1) stringResource(R.string.library_songs)else stringResource(R.string.song))
        }
    }
}