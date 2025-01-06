package com.byrondev.musicplayer.components.searchbar

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.byrondev.musicplayer.viewModels.MusicViewModels
import com.byrondev.musicplayer.viewModels.PlayerViewModels


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarApp(
    query : MutableState<String>,
    musicViewModels: MusicViewModels,
    active : MutableState<Boolean>,
    playerViewModels: PlayerViewModels,
    navController: NavController,
    // Todo add result if required (optional) or test
    ) {

    GridItemSpan(currentLineSpan = 1)
    Row(modifier = Modifier.fillMaxWidth().padding(end = 15.dp), verticalAlignment = Alignment.CenterVertically) {
        // Todo add search bar component
    }
}
