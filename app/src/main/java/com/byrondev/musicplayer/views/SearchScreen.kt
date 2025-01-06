package com.byrondev.musicplayer.views


//import androidx.compose.ui.geometry.Offset
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.byrondev.musicplayer.components.BottomBar
import com.byrondev.musicplayer.viewModels.MusicViewModels
import com.byrondev.musicplayer.viewModels.PlayerViewModels


@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun SearchScreen(
    navController: NavController,
    musicViewModels: MusicViewModels,
    playerViewModels: PlayerViewModels,
    ) {

    val query  =  remember { mutableStateOf("") }
    val active = remember { mutableStateOf(false) }
    // val result /* Todo complete this variable*/

    LaunchedEffect(Unit) {
        musicViewModels.clearSearchMusicResult()
    }

    Box(
        modifier = Modifier.fillMaxSize().background(Color.Black)
    ) {
        Column (modifier = Modifier.fillMaxSize()) {
            // Todo add SearchBarApp component
//            SearchBarApp(query, musicViewModels, active, results=result, playerViewModels, navController)
            // Todo get result to show
//            SearchContentResult( query, result,musicViewModels,navController, playerViewModels)
        }
        BottomBar(navController, modifier = Modifier.align(Alignment.BottomCenter))
    }

}

