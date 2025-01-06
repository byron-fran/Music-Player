package com.byrondev.musicplayer

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.media3.common.AudioAttributes
import androidx.media3.common.C
import androidx.media3.exoplayer.ExoPlayer
import com.byrondev.musicplayer.navigation.NavManager
import com.byrondev.musicplayer.ui.theme.MusicPlayerTheme
import com.byrondev.musicplayer.viewModels.MusicViewModels
import com.byrondev.musicplayer.viewModels.PlayerViewModels
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var player: ExoPlayer

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModels: MusicViewModels by viewModels()
        val playerViewModels : PlayerViewModels by viewModels()
        // Create the ExoPlayer instance
        player = ExoPlayer.Builder(this)
            .setAudioAttributes(
                AudioAttributes.Builder()
                    .setUsage(C.USAGE_MEDIA)
                    .setContentType(C.AUDIO_CONTENT_TYPE_MUSIC)
                    .build(),
                true
            )
            .setHandleAudioBecomingNoisy(true)
            .build()

        enableEdgeToEdge()

        setContent {
            MusicPlayerTheme {
                NavManager(viewModels, player, playerViewModels)
            }
        }

    }
    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }
}





