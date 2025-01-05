package com.byrondev.musicplayer.components.modals

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.byrondev.musicplayer.R
import com.byrondev.musicplayer.components.menu.MenuItem
import com.byrondev.musicplayer.data.models.Song
import com.byrondev.musicplayer.ui.theme.Gray10
import com.byrondev.musicplayer.ui.theme.textDarkGray13
import com.byrondev.musicplayer.ui.theme.textWhite15
import com.byrondev.musicplayer.utils.decodeBitmapWithSubsampling

@RequiresApi(Build.VERSION_CODES.S)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PartialBottomSheet (
    showBottomSheet : MutableState<Boolean>,
    song: Song
) {

    val coverBitMap = song.cover?.let { decodeBitmapWithSubsampling(it, 300, 300) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)

    if (showBottomSheet.value) {
        ModalBottomSheet (
            modifier = Modifier.height(350.dp),
            sheetState = sheetState,
            onDismissRequest = { showBottomSheet.value = false },
            containerColor = Gray10
        ) {

            LazyColumn (){
                item {
                    Row (
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier.padding(horizontal =  5.dp)
                    ) {
                        if(coverBitMap != null){
                            Image(
                                bitmap = coverBitMap.asImageBitmap(),
                                contentDescription = "",
                                modifier = Modifier.size(50.dp)
                            )
                        }
                        Column (
                            modifier = Modifier,
                            verticalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Text("${song.title}", style = textWhite15)
                            Spacer(modifier = Modifier.height(10.dp))
                            Text("${song.album}", style = textDarkGray13)
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(15.dp))
                }
                items(1) {
                    MenuItem(
                        icon = painterResource(id= R.drawable.playlist_add),
                        size = 30.dp,
                        title =  "Add to playlist"

                    )
                    MenuItem(
                        icon = painterResource(id= R.drawable.baseline_delete_outline_30),
                        size = 30.dp,
                        title =  "Delete song"

                    )
                    MenuItem(
                        icon = painterResource(id= R.drawable.baseline_play_circle_outline_30),
                        size = 30.dp,
                        title =  "Play next song"

                    )
                }
            }
        }
    }

}
