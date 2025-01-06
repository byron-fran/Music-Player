package com.byrondev.musicplayer.components.search

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.byrondev.musicplayer.data.dao.SearchResult
import com.byrondev.musicplayer.ui.theme.textDarkGray13
import com.byrondev.musicplayer.viewModels.MusicViewModels
import com.byrondev.musicplayer.viewModels.PlayerViewModels

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun SearchContentResult (
    query : MutableState<String>,
    results : State<List<SearchResult>>,
    musicViewModels: MusicViewModels,
    navController: NavController,
    playerViewModels: PlayerViewModels
){

    LaunchedEffect(Unit) {
        musicViewModels.clearSearchMusicResult()
    }

    val filterSelected = remember { mutableStateOf("All results") }

    val resultsFiltered by remember(results, filterSelected.value) {
        derivedStateOf {
            when (filterSelected.value) {
                "Albums" -> results.value.filter { it.type == "Album" }
                "Songs" -> results.value.filter { it.type == "Song" }
                "Artists" -> results.value.filter { it.type == "Artist" }
                else -> results.value
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize())
    {

        Column (modifier = Modifier.fillMaxSize().padding(  top = 10.dp)) {
            Row (
                modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ){
                CardButtonResult("All results",) { filterSelected.value = "All results"}
                CardButtonResult("Albums",) {filterSelected.value = "Albums" }
                CardButtonResult("Songs",) { filterSelected.value = "Songs" }
                CardButtonResult("Artists",) { filterSelected.value = "Artists"}
            }
            if(resultsFiltered.isNotEmpty() ) {

                LazyColumn (
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.padding(top = 15.dp, start = 5.dp)
                ){
                    items(resultsFiltered) {result ->
                        when(result.type) {
                            "Album" ->  CardAlbumSearch(navController, result)

                            "Song" -> CardSongResult(result, playerViewModels)

                            else ->  CardArtistResult(result)
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun CardButtonResult (title : String, onClick : () -> Unit) {
    Button(
        onClick =  { onClick() },
        colors =  ButtonDefaults.buttonColors(
            containerColor =  Color.White
        ),
        shape = RoundedCornerShape(20.dp)
    )  {

        Text(title, color = Color.Black, style =  textDarkGray13 )
    }
}