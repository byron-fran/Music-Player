package com.byrondev.musicplayer.tabs

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.byrondev.musicplayer.R
import com.byrondev.musicplayer.components.globals.TextMedium
import com.byrondev.musicplayer.components.playlist.PlaylistItems
import com.byrondev.musicplayer.viewModels.MusicViewModels
import com.byrondev.musicplayer.viewModels.PlayerViewModels
import com.byrondev.musicplayer.views.albums.AlbumsScreen
import com.byrondev.musicplayer.views.artists.ArtistScreen
import com.byrondev.musicplayer.views.genres.GenresScreen
import com.byrondev.musicplayer.views.songs.SongsScreen

typealias StringFun = @Composable () -> Unit
typealias ComposableFun = @Composable (MusicViewModels, PlayerViewModels, NavController) -> Unit

sealed class TabItem(
    val stringRes: StringFun,
    var screen: ComposableFun,
) {
    @RequiresApi(Build.VERSION_CODES.S)
    data object Albums : TabItem(
        stringRes = {
            TextMedium(
                stringResource(id = R.string.library_albums),
                color = Color.Unspecified,
            )
        },
        { musicViewModels, _, navController ->
            AlbumsScreen(
                navController,
                musicViewModels
            )
        }
    )

    @RequiresApi(Build.VERSION_CODES.S)
    data object Songs : TabItem(
        stringRes = {
            TextMedium(
                text = stringResource(id = R.string.library_songs),
                color = Color.Unspecified
            )
        },

        { musicViewModels, playerViewModel, navController ->
            SongsScreen(
                musicViewModels,
                navController,
                playerViewModel
            )
        }
    )

    @RequiresApi(Build.VERSION_CODES.S)
    data object Artists : TabItem(
        stringRes = {
            TextMedium(
                text = stringResource(id = R.string.library_artists),
                color = Color.Unspecified
            )
        },

        { musicViewModels, _, navController ->
            ArtistScreen(
                musicViewModels,
                navController
            )
        })

    @RequiresApi(Build.VERSION_CODES.S)
    data object Playlists : TabItem(
        stringRes = {
            TextMedium(
                text = stringResource(id = R.string.library_playlists),
                color = Color.Unspecified
            )
        },
        { musicViewModels, _, navController ->
            PlaylistItems(
                musicViewModels,
                navController
            )
        }
    )

    @RequiresApi(Build.VERSION_CODES.S)
    data object Genres : TabItem(
        stringRes = {
            TextMedium(
                text = stringResource(id = R.string.library_genre),
                color = Color.Unspecified
            )
        },
        { musicViewModels, _, navController ->
            GenresScreen(
                musicViewModels,
                navController
            )
        }
    )
}