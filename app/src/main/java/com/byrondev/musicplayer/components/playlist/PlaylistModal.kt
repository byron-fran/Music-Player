package com.byrondev.musicplayer.components.playlist

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.byrondev.musicplayer.components.globals.TextLarge
import com.byrondev.musicplayer.components.globals.TextMedium
import com.byrondev.musicplayer.data.models.Playlist
import com.byrondev.musicplayer.ui.theme.Pink60
import com.byrondev.musicplayer.ui.theme.Slate70
import com.byrondev.musicplayer.ui.theme.Slate80
import com.byrondev.musicplayer.ui.theme.textMedium
import com.byrondev.musicplayer.viewModels.MusicViewModels

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun PlaylistModal( musicViewModels: MusicViewModels) {

    val showModalPlaylist = musicViewModels.showModalPlaylistActive.collectAsState()
    val textValue = remember { mutableStateOf("") }
    val context = LocalContext.current
    val toast = Toast.makeText(context, "Playlist created successful", Toast.LENGTH_SHORT)

    if(showModalPlaylist.value){
        Dialog(
            onDismissRequest = {musicViewModels.onChangeValueModal()},
        ) {
            Column {
                TextLarge("Nombre de la playlist",  modifier = Modifier.padding(bottom = 10.dp))
                TextField(
                    value = textValue.value,
                    onValueChange =  {textValue.value = it},
                    modifier = Modifier.fillMaxWidth(),
                    placeholder =  { Text("Nombre de la playlist") },
                    shape =  RoundedCornerShape(5.dp),
                    maxLines = 1,
                    textStyle = textMedium,
                    colors = TextFieldDefaults.colors(
                        focusedLabelColor = Slate70,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor =Color.White,
                        focusedTextColor = Slate80,
                        unfocusedTextColor = Slate80
                    )
                )
                Button(
                    onClick = {
                        if(textValue.value.trim().isNotEmpty()){
                            musicViewModels.insertPlaylist(Playlist( name = textValue.value))
                            textValue.value = ""
                            musicViewModels.onChangeValueModal()
                            toast.setMargin(0F, 20F)
                            toast.show()

                        }
                    },
                    modifier = Modifier.align(Alignment.CenterHorizontally).fillMaxWidth().offset(x = 0.dp, y = 10.dp),
                    colors =  ButtonDefaults.buttonColors(
                        containerColor = Slate80,
                        contentColor = Pink60
                    ),
                    shape = RoundedCornerShape(5.dp)
                ) {
                    TextMedium("Guardar", color = Pink60)
                }
            }
        }
    }
}