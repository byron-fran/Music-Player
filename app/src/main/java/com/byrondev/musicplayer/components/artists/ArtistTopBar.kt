package com.byrondev.musicplayer.components.artists

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtistTopBar(navController: NavController, artistName : String) {

     CenterAlignedTopAppBar(
         title =  { Text(artistName, color=Color.White) },
         navigationIcon = {
             Icon(
                 imageVector = Icons.AutoMirrored.Default.ArrowBack,
                 modifier = Modifier.clickable { navController.popBackStack() },
                 contentDescription = "Icon back",

             ) },

         actions =  {},
         modifier = Modifier.background(Color.Black),
         colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
             containerColor = Color.Black,
             actionIconContentColor = Color.White,
             navigationIconContentColor = Color.White
         )
     )

}