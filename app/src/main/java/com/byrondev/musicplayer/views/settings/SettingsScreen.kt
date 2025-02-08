package com.byrondev.musicplayer.views.settings

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.byrondev.musicplayer.R
import com.byrondev.musicplayer.components.files.SelectedLaunchedFiles
import com.byrondev.musicplayer.components.settings.SettingsCard
import com.byrondev.musicplayer.components.topbar.CenterTopAppBar
import com.byrondev.musicplayer.viewModels.MusicViewModels
import com.byrondev.musicplayer.viewModels.PlayerViewModels

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun SettingsScreen(
    navController: NavController,
    musicViewModels: MusicViewModels,
    playerViewModels: PlayerViewModels
    ) {

    Scaffold(
        topBar = { CenterTopAppBar("Settings", ) {navController.popBackStack()} },
        containerColor = Color.Black,
        contentColor =  Color.White,

    ) { paddingValues-> SettingsScreenContent(paddingValues, musicViewModels) }

}

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun SettingsScreenContent(paddingValues: PaddingValues, musicViewModels: MusicViewModels) {

    Box(
        modifier = Modifier.fillMaxSize().padding(paddingValues),

    ){
        Column (

        ) {
            SelectedLaunchedFiles(musicViewModels) {
                SettingsCard("Seleccionar Música", painterResource(id = R.drawable.baseline_cd_30)) {it.launch(null) }
            }
        }
    }

}