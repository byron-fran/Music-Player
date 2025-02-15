package com.byrondev.musicplayer

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import com.byrondev.musicplayer.layout.LayoutScreen
import com.byrondev.musicplayer.ui.theme.MusicPlayerTheme
import com.byrondev.musicplayer.viewModels.MusicViewModels
import com.byrondev.musicplayer.viewModels.PlayerViewModels
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val musicViewModels: MusicViewModels by viewModels()
        val playerViewModels: PlayerViewModels by viewModels()
        enableEdgeToEdge()

        setContent {
            MusicPlayerTheme {
                LayoutScreen(playerViewModels, musicViewModels)
            }
        }
    }
}