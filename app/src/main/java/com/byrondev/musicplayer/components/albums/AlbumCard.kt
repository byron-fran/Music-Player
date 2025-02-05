package com.byrondev.musicplayer.components.albums

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.byrondev.musicplayer.R
import com.byrondev.musicplayer.components.globals.CircleSeparation
import com.byrondev.musicplayer.components.globals.LazyImageCover
import com.byrondev.musicplayer.components.globals.TextExtraSmall
import com.byrondev.musicplayer.components.globals.TextMedium
import com.byrondev.musicplayer.data.databaseViews.AlbumResponse
import com.byrondev.musicplayer.utils.dates.parseYear


@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun AlbumCard(
    album: AlbumResponse,
    navController: NavController,
    modifier: Modifier = Modifier,
    ) {


    Card(
        modifier = modifier.clickable { navController.navigate("AlbumDetail/${album.id}") },
        colors = CardDefaults.cardColors(containerColor = Color.Black),
        shape = RoundedCornerShape(0.dp)
    ) {
        LazyImageCover(
            album.cover,
            painterResource(id = R.drawable.image_cd_default),
            modifier = Modifier.aspectRatio(1f)
        )
        Row(
            modifier = modifier
                .padding(top = 5.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = modifier
                    .weight(1f)
                    .background(Color.Black),
                verticalArrangement = Arrangement.spacedBy(2.dp),
            ) {
                TextMedium(album.title)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    val year = parseYear(album.year)
                    TextExtraSmall(text = album.artist)
                    if (year != null) {
                        CircleSeparation()
                        TextExtraSmall(text = year.toString())
                    }
                }
            }
            album.numOfHiresQuality.let {
                if (it > 0) {
                    Image(
                        painter = painterResource(id = R.drawable.hi_res_logo),
                        modifier = Modifier.size(25.dp),
                        contentDescription = ""
                    )
                }
            }
        }
    }
}