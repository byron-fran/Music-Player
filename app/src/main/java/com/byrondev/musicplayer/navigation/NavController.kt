package com.byrondev.musicplayer.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.media3.exoplayer.ExoPlayer
import androidx.navigation.NavController
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
import com.byrondev.musicplayer.views.artists.AlbumsByArtist
import com.byrondev.musicplayer.views.artists.ArtistDetailScreen
import com.byrondev.musicplayer.views.genres.SongsByGenre
import com.byrondev.musicplayer.views.playlist.PlaylistDetailScreen
import com.byrondev.musicplayer.views.playlist.PlaylistScreen
import com.byrondev.musicplayer.views.settings.SettingsScreen
import com.byrondev.musicplayer.views.songs.SongsByArtist


@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun NavManager(
    musicViewModels: MusicViewModels,
    player: ExoPlayer,
    playerViewModels: PlayerViewModels,
    content : @Composable (navController : NavController) -> Unit

    ) {
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
        // Artist detail screen
        composable("ArtistDetail/{id}", arguments = listOf(
            navArgument("id") {type = NavType.IntType}
        )) {backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            ArtistDetailScreen(navController, musicViewModels, playerViewModels, id)
        }
        // Songs by artist screen
        composable("SongsByArtist/{id}", arguments = listOf(
            navArgument("id") {type = NavType.IntType}),
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {

                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            }
            ) {backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            SongsByArtist(navController, musicViewModels, playerViewModels, id)
        }
        // Albums by artist screen
        composable("AlbumsByArtist/{id}", arguments = listOf(
            navArgument("id") {type = NavType.IntType}),
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {

                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            }) {backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            AlbumsByArtist(navController, musicViewModels, id)
        }

        // Album detail Screen
        composable("AlbumDetail/{id}", arguments = listOf(
            navArgument("id") { type = NavType.IntType }
        )) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            AlbumDetail(navController, musicViewModels,playerViewModels,id)
        }
        // Favorites Screen
        composable("FavoritesScreen") {
            FavoritesScreen(navController, musicViewModels,playerViewModels)
        }
        // Songs by genre Screen
        composable("SongsByGenreScreen/{id}", arguments = listOf(
            navArgument("id"){ type = NavType.IntType}
        )) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?:0
            SongsByGenre(navController, musicViewModels,playerViewModels,id)
        }
        //Test
        composable("PlaylistScreen/{songId}", arguments = listOf(
            navArgument("songId"){type=NavType.IntType}
        )) {backStackEntry ->
            val songId = backStackEntry.arguments?.getInt("songId") ?: 0
            PlaylistScreen(musicViewModels, navController,songId)
        }
        // Search Screen
        composable("SearchScreen",

            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {

                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            }
            ) {
            SearchScreen(navController, musicViewModels, playerViewModels)
        }
        composable("SettingsScreen",
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {

                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            }

            ) {
            SettingsScreen(navController, musicViewModels, playerViewModels)
        }
        // Playlist detail screen
        composable("PlaylistDetailScreen/{id}", arguments = listOf(
            navArgument("id") {type = NavType.IntType}
        )){ navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getInt("id") ?: 0
            PlaylistDetailScreen(navController, musicViewModels, playerViewModels, id)
        }
    }
    content(navController)
}
