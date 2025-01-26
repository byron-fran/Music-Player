package com.byrondev.musicplayer.views

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.byrondev.musicplayer.components.library.LibraryHeader
import com.byrondev.musicplayer.tabs.TabItem
import com.byrondev.musicplayer.ui.theme.Rose60
import com.byrondev.musicplayer.ui.theme.Slate70
import com.byrondev.musicplayer.ui.theme.textMedium
import com.byrondev.musicplayer.viewModels.MusicViewModels
import com.byrondev.musicplayer.viewModels.PlayerViewModels
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun LibraryScreen(
    navController: NavController,
    musicViewModels: MusicViewModels,
    playerViewModels: PlayerViewModels,
    ) {

    val tabs = listOf(TabItem.Albums, TabItem.Songs, TabItem.Artists, TabItem.Playlists, TabItem.Genres)
    val pagerState = rememberPagerState (pageCount = { tabs.size })
    val scope = rememberCoroutineScope()

    Box (
        modifier = Modifier.fillMaxSize()
    ){
        Column(modifier = Modifier.background(color=Color.Black).fillMaxSize().padding(top = 50.dp)) {
            LibraryHeader(navController, musicViewModels)
            Spacer(modifier=Modifier.height(5.dp).fillMaxWidth())
            ScrollableTabRow  (
                selectedTabIndex = pagerState.currentPage,
                containerColor = Color.Transparent,
                contentColor = Color.Transparent,
                edgePadding =  0.dp,
                indicator =  {

                },
                divider =  { HorizontalDivider(color =  Slate70, thickness = 1.dp) }
            ) {
                tabs.forEachIndexed { index, tab ->
                    // OR Tab()
                    Tab (

                        text = { Text(tab.title, style = textMedium) },
                        selected = pagerState.currentPage == index,
                        selectedContentColor = Rose60,
                        unselectedContentColor = Color.White,
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                    )
                }
            }
            TabsContent(tabs, pagerState, musicViewModels,playerViewModels, navController)
        }
    }
}

@Composable
fun TabsContent(
    tabs: List<TabItem>,
    pagerState: PagerState,
    musicViewModels: MusicViewModels,
    playerViewModels: PlayerViewModels,
    navController: NavController
    ) {
    HorizontalPager (state = pagerState) { page ->
        tabs[page].screen(musicViewModels, playerViewModels, navController)
    }
}