package com.byrondev.musicplayer.components.search

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.byrondev.musicplayer.R
import com.byrondev.musicplayer.ui.theme.Slate70
import com.byrondev.musicplayer.ui.theme.Slate80
import com.byrondev.musicplayer.ui.theme.Zinc40
import com.byrondev.musicplayer.viewModels.MusicViewModels
import com.byrondev.musicplayer.viewModels.PlayerViewModels


@RequiresApi(Build.VERSION_CODES.S)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarApp(
    query : MutableState<String>,
    musicViewModels: MusicViewModels,
    active : MutableState<Boolean>,
    playerViewModels: PlayerViewModels,
    onClick : () -> Unit,
    content : @Composable  () -> Unit
    ) {

    GridItemSpan(currentLineSpan = 1)
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        SearchBar(
            query = query.value,
            onQueryChange =  {
                query.value = it
                if(query.value.trim().length > 1) {
                    musicViewModels.searchMusicByQuery(query.value)
                    active.value = true
                }
                else {
                    active.value = false
                } },
            onSearch = {active.value = true},
            onActiveChange = {},
            active = active.value,
            placeholder = {
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    ){
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "",
                    )
                    Text("Search by song, artist or album")
                }
            },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth().background(Color.Black),
            colors = SearchBarDefaults.colors(
                containerColor = Slate80,
                dividerColor = Slate70,
                inputFieldColors = TextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Zinc40
                )
            ),
            leadingIcon =  {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    modifier = Modifier.clickable { onClick() }.size(30.dp),
                    tint = Zinc40,
                    contentDescription = ""
                )
            },
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_close_30),
                    modifier = Modifier.clickable {
                        query.value = ""
                        active.value = false}
                        .size(30.dp),
                    tint = Zinc40,
                    contentDescription =  ""
                )
            },
        ) {
            content()
        }
    }
}
