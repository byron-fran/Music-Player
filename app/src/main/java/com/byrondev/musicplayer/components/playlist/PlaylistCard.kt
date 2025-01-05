package com.byrondev.musicplayer.components.playlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.byrondev.musicplayer.ui.theme.textDarkGray13
import com.byrondev.musicplayer.ui.theme.textWhite15


@Composable
fun PlaylistCard() {
    Card(
        modifier = Modifier.fillMaxWidth().height(200.dp).clickable {   /* Todo add event */  },
        colors =  CardDefaults.cardColors(
            containerColor =  Color.Transparent
        ),
        shape = RoundedCornerShape(5.dp),

    ) {
        Row {
            Card(
                modifier = Modifier.weight(1f).height(80.dp).alpha(0.5f),
                shape =  RoundedCornerShape(0.dp),
            ) { Text("Playlist 1") }
            Card (
                modifier = Modifier.weight(1f).height(80.dp).alpha(0.5f),
                colors =  CardDefaults.cardColors(containerColor =  Color.Cyan),
                shape =  RoundedCornerShape(0.dp),
            ){ Text("Playlist 2") }
        }
//        Row 2
        Row {
            Card(
                modifier = Modifier.weight(1f).height(80.dp).alpha(0.5f),
                colors =  CardDefaults.cardColors(
//                    containerColor =  Todo add Color
                ),
                shape =  RoundedCornerShape(0.dp),
            ) { Text("Playlist 1") }
            Card (
                modifier = Modifier.weight(1f).height(80.dp).alpha(0.5f),
                colors =  CardDefaults.cardColors(/* Todo add container Color*/ ),
                shape =  RoundedCornerShape(0.dp),
            ){ Text("Playlist 2") }
        }
        Column (
            modifier = Modifier.padding(2.dp),

        ) {
            Text("PlayList Rap 2024", maxLines = 1, overflow = TextOverflow.Ellipsis, style = textWhite15, modifier = Modifier.offset(x= 0.dp, y = 2.dp))
            Text("50 songs", style = textDarkGray13, modifier = Modifier.offset(x = 0.dp, y =2.dp))
        }

    }
}