package com.byrondev.musicplayer.components.albums

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.byrondev.musicplayer.R
import com.byrondev.musicplayer.data.models.Album
import com.byrondev.musicplayer.ui.theme.Gray10
import com.byrondev.musicplayer.ui.theme.textDarkGray13
import com.byrondev.musicplayer.viewModels.MusicViewModels

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun ButtonsPlayAlbum(musicViewModels: MusicViewModels, albumCount : Int?, album: Album?) {

    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 20.dp, horizontal = 15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
        ) {

        Column {
            if(albumCount!! > 0) {
                Text("$albumCount Songs", style = textDarkGray13)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text("FLAC 24 bits / 48khz", style = textDarkGray13) // Todo add real data
        }

        // Row Buttons Cards
        Row (
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            // Card button play
            Card (
                modifier = Modifier.size(50.dp).clickable { /* Todo add event to play*/ },
                shape = CircleShape,
                colors = CardDefaults.cardColors(containerColor = Gray10, contentColor = Color.White)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "icon to Play",
                        tint = Color.White,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }

            //Card Button shuffle
            Card (
                modifier = Modifier.size(50.dp),
                shape = CircleShape,
                colors = CardDefaults.cardColors(containerColor = Gray10, contentColor = Color.White)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.shuffle),
                        contentDescription = "icon to Play",
                        tint = Color.White,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
            // End Card Button shuffle
        }
    }
}