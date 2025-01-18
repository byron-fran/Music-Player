package com.byrondev.musicplayer.components.playlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.byrondev.musicplayer.R
import com.byrondev.musicplayer.data.dao.PlaylistWithCountSong
import com.byrondev.musicplayer.ui.theme.Slate80

@Composable
fun PlaylistCard(playlist: PlaylistWithCountSong, onClick : () -> Unit) {

    Box(
        modifier = Modifier
            .width(185.dp)
            .height(230.dp)
            .background(Color.Black)
            .clickable {  onClick()},
        contentAlignment = Alignment.Center
    ) {
        Card (
            modifier = Modifier.fillMaxWidth().height(185.dp).align(Alignment.TopStart),
            colors = CardDefaults.cardColors(containerColor = Slate80),
            shape = RoundedCornerShape(5.dp),
        ) {
            Box (
                modifier = Modifier.fillMaxSize().background(Color.Transparent),
                contentAlignment = Alignment.Center
            ){
                Image(
                    painter = painterResource(id = R.drawable.baseline_music_note_600),
                    modifier = Modifier.size(100.dp),
                    contentDescription =  "",
                )
            }
        }
        PlaylistInfo(playlist, modifier = Modifier.align(Alignment.BottomStart))
    }
}