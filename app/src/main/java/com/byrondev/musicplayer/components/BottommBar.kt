package com.byrondev.musicplayer.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.byrondev.musicplayer.R
import com.byrondev.musicplayer.ui.theme.Rose60
import com.byrondev.musicplayer.ui.theme.Slate70
import com.byrondev.musicplayer.ui.theme.textSmall

data class MenuItemBottom(
    val title: String,
    val icon : Painter,
    val size : Dp = 30.dp,
    val path : String = ""

)
@Composable
fun BottomBar(navController: NavController, modifier: Modifier = Modifier) {

    val listMenuItemBottom = listOf(
        MenuItemBottom(title = "Home", icon = painterResource(id=R.drawable.home), path = "HomeScreen" ),
        MenuItemBottom(title = "Library", icon = painterResource(id=R.drawable.music), path = "LibraryScreen"),
        MenuItemBottom(title = "Favorites", icon = painterResource(id=R.drawable.favorite_filled), path="SongDetailPlaying/${6}"),
        MenuItemBottom(title = "Search", icon = painterResource(id=R.drawable.search), path = "SearchScreen"),
    )

    NavigationBar (
        modifier = modifier

            .background(color = Color.Black)
            .drawBehind {
                val strokeWidth = 2.dp.toPx() // Define el grosor del borde
                val color = Slate70 // Color del borde
                drawLine(
                    color = color,
                    start = Offset(0f, 0f), // Comienzo de la línea (izquierda superior)
                    end = Offset(size.width, 0f), // Fin de la línea (derecha superior)
                    strokeWidth = strokeWidth
                )
            }
            .height(65.dp)
        ,
        containerColor = Color.Black,

    ) {
        listMenuItemBottom.forEach { item ->

            NavigationBarItem(
                selected = item.path == navController.currentBackStackEntry?.destination?.route.toString(),
                onClick = { navController.navigate(item.path) },
                icon = { Icon(item.icon, contentDescription = "Icon ${item.title}", modifier = Modifier.offset(x= 0.dp, y=10.dp)) },
                label = { Text(item.title, style = textSmall,  modifier= Modifier.offset(x = 0.dp, y = 2.dp)) },
                modifier = Modifier.size(item.size).align(Alignment.Bottom),
                colors =  NavigationBarItemColors(
                    selectedIconColor = Rose60,
                    selectedTextColor = Rose60,
                    disabledIconColor = Color.White,
                    disabledTextColor = Color.White,
                    selectedIndicatorColor = Color.Transparent,
                    unselectedIconColor = Color.White,
                    unselectedTextColor = Color.White
                )
            )
        }
    }
}