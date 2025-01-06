package com.byrondev.musicplayer.tabs

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.byrondev.musicplayer.viewModels.MusicViewModels
import com.byrondev.musicplayer.viewModels.PlayerViewModels
import com.byrondev.musicplayer.views.albums.AlbumsScreen
import com.byrondev.musicplayer.views.artists.ArtistScreen
import com.byrondev.musicplayer.views.songs.SongsScreen

typealias ComposableFun = @Composable ( MusicViewModels, PlayerViewModels, NavController ) -> Unit

sealed class TabItem( var title: String, var screen: ComposableFun) {
    @RequiresApi(Build.VERSION_CODES.S)
    data object Albums : TabItem( "Albums", { musicViewModels, playerViewModel, navController -> AlbumsScreen( navController,musicViewModels) } )
    @RequiresApi(Build.VERSION_CODES.S)
    data object Songs : TabItem( "Songs", { musicViewModels, playerViewModel, navController ->   SongsScreen(musicViewModels, navController, playerViewModel) })
    @RequiresApi(Build.VERSION_CODES.S)
    data object Artists : TabItem( "Artists", { musicViewModels, _, navController ->  ArtistScreen(musicViewModels, navController) })

}
