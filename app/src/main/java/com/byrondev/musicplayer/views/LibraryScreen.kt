package com.byrondev.musicplayer.views

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LeadingIconTab
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.byrondev.musicplayer.components.BottomBar
import com.byrondev.musicplayer.tabs.TabItem
import com.byrondev.musicplayer.viewModels.MusicViewModels
import com.byrondev.musicplayer.viewModels.PlayerViewModels
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun LibraryScreen(navController: NavController, musicViewModels: MusicViewModels, playerViewModels: PlayerViewModels) {

    Scaffold(
        bottomBar = { BottomBar(navController) },
        content = { paddingValues ->
            LibraryScreenContent(navController, paddingValues, musicViewModels, playerViewModels)
        }
    )
}

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun LibraryScreenContent(
    navController: NavController,
    paddingValues: PaddingValues,
    musicViewModels: MusicViewModels,
    playerViewModels: PlayerViewModels,
) {

    val tabs = listOf(TabItem.Albums, TabItem.Songs, TabItem.Artists)
    val pagerState = rememberPagerState (pageCount = {
        tabs.size
    })
    val scope = rememberCoroutineScope()
    Column(modifier = Modifier.background(color=Color.Black).fillMaxSize().padding(paddingValues)) {

        Spacer(modifier=Modifier.height(10.dp).fillMaxWidth())

        TabRow (
            selectedTabIndex = pagerState.currentPage,
            containerColor = Color.Transparent,
            contentColor = Color.Transparent,
            indicator =  {},
            divider =  {
                HorizontalDivider( /* Todo add color */ thickness = 1.dp)
            }
            ) {
            tabs.forEachIndexed { index, tab ->
                // OR Tab()
                LeadingIconTab(
                    icon = { },
                    text = { Text(tab.title, fontSize = 17.sp) },
                    selected = pagerState.currentPage == index,
//                    selectedContentColor = Pink60, Todo add color
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