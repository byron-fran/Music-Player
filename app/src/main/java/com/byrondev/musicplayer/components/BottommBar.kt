package com.byrondev.musicplayer.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.byrondev.musicplayer.R
import com.byrondev.musicplayer.ui.theme.Gray
import com.byrondev.musicplayer.ui.theme.Rose60
import com.byrondev.musicplayer.ui.theme.Slate70
import com.byrondev.musicplayer.ui.theme.textSmall

data class MenuItemBottom(
    val title: String,
    val icon: Painter,
    val size: Dp = 30.dp,
    val path: String = "",

    )

@Composable
fun BottomBar(navController: NavController, modifier: Modifier = Modifier) {

    val listMenuItemBottom = listOf(
        MenuItemBottom(
            title = stringResource(id = R.string.tab_home),
            icon = painterResource(id = R.drawable.home),
            path = "HomeScreen"
        ),
        MenuItemBottom(
            title = stringResource(id = R.string.tab_library),
            icon = painterResource(id = R.drawable.music),
            path = "LibraryScreen"
        ),
        MenuItemBottom(
            title = stringResource(id = R.string.tab_favorite),
            icon = painterResource(id = R.drawable.favorite_filled),
            path = "FavoritesScreen"
        ),
        MenuItemBottom(
            title = stringResource(id = R.string.tab_search),
            icon = painterResource(id = R.drawable.search),
            path = "SearchScreen"
        ),
    )
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    NavigationBar(
        modifier = modifier
            .background(color = Color.DarkGray)
            .drawBehind {
                val strokeWidth = 1.dp.toPx()
                val color = Slate70
                drawLine(
                    color = color,
                    start = Offset(0f, 0f),
                    end = Offset(size.width, 0f),
                    strokeWidth = strokeWidth
                )
            }
            .height(50.dp),
        containerColor = Color.Black,

        ) {
        listMenuItemBottom.forEach { item ->
            NavigationBarItem(
                selected = item.path == currentRoute,
                onClick = { navController.navigate(item.path) },
                icon = {
                    Icon(
                        item.icon,
                        contentDescription = "Icon ${item.title}",
                        modifier = Modifier.offset(x = 0.dp, y = 17.dp)
                    )
                },
                label = {
                    Text(
                        item.title,
                        color = Color.Unspecified,
                        style = textSmall,
                        modifier = Modifier
                            .offset(x = 0.dp, y = 8.dp),
                    )
                },
                modifier = Modifier.align(Alignment.Top),
                colors = NavigationBarItemColors(
                    selectedIconColor = Rose60,
                    selectedTextColor = Rose60,
                    disabledIconColor = Gray,
                    disabledTextColor = Gray,
                    selectedIndicatorColor = Color.Transparent,
                    unselectedIconColor = Gray,
                    unselectedTextColor = Gray
                )
            )
        }
    }
}