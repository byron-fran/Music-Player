package com.byrondev.musicplayer.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.media3.exoplayer.ExoPlayer
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.byrondev.musicplayer.viewModels.MusicViewModels
import com.byrondev.musicplayer.viewModels.PlayerViewModels
import com.byrondev.musicplayer.views.FavoritesScreen
import com.byrondev.musicplayer.views.HomeScreen
import com.byrondev.musicplayer.views.LibraryScreen
import com.byrondev.musicplayer.views.SearchScreen
import com.byrondev.musicplayer.views.albums.AlbumDetail
import com.byrondev.musicplayer.views.albums.AlbumsScreen


@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun NavManager(musicViewModels: MusicViewModels, player: ExoPlayer, playerViewModels: PlayerViewModels) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "LibraryScreen") {
        // Home Screen
        composable("HomeScreen") {
            HomeScreen(navController, musicViewModels)
        }
        // Albums Screen
        composable("AlbumsScreen") {
            AlbumsScreen(navController, musicViewModels)
        }
        // Library Screen
        composable("LibraryScreen") {
            LibraryScreen(navController, musicViewModels, playerViewModels)
        }
        // Album detail Screen
        composable("AlbumDetail/{id}", arguments = listOf(
            navArgument("id") { type = NavType.IntType }
        )) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            AlbumDetail(navController, musicViewModels, player,id)
        }
        // Favorites Screen
        composable("FavoritesScreen") {
            FavoritesScreen(navController, musicViewModels,)
        }
        // Search Screen
        composable("SearchScreen",

            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Up,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {

                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Down,
                    animationSpec = tween(700)
                )
            }
            ) {
            SearchScreen(navController, musicViewModels, playerViewModels)
        }
    }
}


