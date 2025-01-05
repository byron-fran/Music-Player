package com.byrondev.musicplayer.components.songs

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.byrondev.musicplayer.R
import com.byrondev.musicplayer.components.modals.PartialBottomSheet
import com.byrondev.musicplayer.data.models.Song
import com.byrondev.musicplayer.ui.theme.Blue70
import com.byrondev.musicplayer.ui.theme.Blue80
import com.byrondev.musicplayer.ui.theme.textDarkGray13
import com.byrondev.musicplayer.ui.theme.textWhite15


@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun SongCard(
    song: Song,
    showTrackNumber : Boolean = true,
    onClick : () -> Unit
    ) {

    val showBottomSheet = remember { mutableStateOf(false) }

    Card(
        onClick = { onClick() },
        modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(
            contentColor = Color.Black,
            containerColor = Color.Black

            )
    ) {

        Row(
            modifier = Modifier.padding(5.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                if(showTrackNumber){
                    Text("${song.trackNumber ?: ""}", style = textDarkGray13, modifier = Modifier.width(20.dp))
                }
                Column(
                    modifier = Modifier.padding(start = 10.dp),
                    horizontalAlignment = Alignment.Start,
                ) {
                    Text(song.title ?: "", style = textWhite15)
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(song.artist ?: "Unknown", style = textDarkGray13)

                }
            }
            Row {
                song.audioBitDepth.let {
                    if (it != null) {
                        if (it >= 24) {
                            Image(
                                painter = painterResource(id = R.drawable.hi_res_logo),
                                contentDescription = "",
                                modifier = Modifier.size(30.dp)
                            )
                        } else if(song.audioBitDepth == 16 ) {
                            Card (
                                modifier = Modifier,
                                border = BorderStroke(1.dp, Blue80),
                                colors =  CardDefaults.cardColors(containerColor = Color.Transparent),
                                shape = RoundedCornerShape(5.dp)
                            ) {
                                Text("CD", style = textDarkGray13, fontSize = 10.sp,  color = Blue70, modifier = Modifier.padding(5.dp))
                            }
                        }
                    }
                }
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.fillMaxHeight().size(20.dp).clickable { showBottomSheet.value = true }
                )
            }
        }

    }
    // Sheet Modal
    PartialBottomSheet(showBottomSheet, song)
}