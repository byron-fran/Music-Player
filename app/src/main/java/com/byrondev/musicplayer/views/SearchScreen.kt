package com.byrondev.musicplayer.views

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.byrondev.musicplayer.components.BottomBar
import com.byrondev.musicplayer.components.globals.EmptyScreen
import com.byrondev.musicplayer.components.globals.TextLarge
import com.byrondev.musicplayer.components.search.SearchBarApp
import com.byrondev.musicplayer.components.search.SearchContentResult
import com.byrondev.musicplayer.ui.theme.Zinc40
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
    val result = musicViewModels.searchResult.collectAsState()

    LaunchedEffect(Unit) {
        musicViewModels.clearSearchMusicResult()
    }

    Box(
        modifier = Modifier.fillMaxSize().background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Column (modifier = Modifier.fillMaxSize()) {
          SearchBarApp(
              query,
              musicViewModels,
              active,
              playerViewModels,
              onClick = {navController.popBackStack()},
              ) { SearchContentResult( query, result,musicViewModels,navController, playerViewModels) }
        }
        if(!active.value ) {
            EmptyScreen() { TextLarge("Search something", color = Zinc40) }
        }
        BottomBar(navController, modifier = Modifier.align(Alignment.BottomCenter))
    }
}

