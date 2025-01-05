package com.byrondev.musicplayer.views

import android.os.Build
import androidx.annotation.OptIn
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.media3.common.util.UnstableApi
import androidx.navigation.NavController
import com.byrondev.musicplayer.R
import com.byrondev.musicplayer.components.BottomBar
import com.byrondev.musicplayer.components.files.SelectedLaunchedFiles
import com.byrondev.musicplayer.components.home.CardHome
import com.byrondev.musicplayer.components.topbar.CenterTopAppBar
import com.byrondev.musicplayer.viewModels.MusicViewModels

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun HomeScreen(navController: NavController, musicViewModels: MusicViewModels) {
    Scaffold(
        topBar = { CenterTopAppBar("Home", Icons.Default.Settings ) { navController.navigate("SettingsScreen")} },
        content = { paddingValues -> HomeScreenContent(navController, paddingValues, musicViewModels) },
        bottomBar = { BottomBar(navController) },
        containerColor = Color.Black,

    )

}

@RequiresApi(Build.VERSION_CODES.S)
@OptIn(UnstableApi::class)
@Composable
fun HomeScreenContent(
    navController: NavController,
    paddingValues: PaddingValues,
    musicViewModels: MusicViewModels
) {
    // List of items home
    LazyColumn(
        modifier = Modifier.padding(paddingValues).fillMaxSize(),
    )
    {
        items(1) {
            SelectedLaunchedFiles(musicViewModels) {
                CardHome(onClick = {it.launch(null)}, "Agregar música", painterResource(id=R.drawable.baseline_add_30))
            }
            CardHome(onClick = { /* Todo add event*/ }, "Agregar nueva lista de reproduccion", painterResource(id=R.drawable.baseline_add_30))
            CardHome(onClick = { navController.navigate("FavoritesScreen") }, "Favoritos", painterResource(id=R.drawable.favorite_filled))
            CardHome(onClick = { /* Todo add event*/ }, "Historial de reproducciones", painterResource(id=R.drawable.baseline_cd_30))
        }
        // Todo remove or change location
    }
}





