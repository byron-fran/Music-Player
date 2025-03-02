package com.byrondev.musicplayer.components.search

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.byrondev.musicplayer.R
import com.byrondev.musicplayer.components.globals.IconSmall
import com.byrondev.musicplayer.components.globals.TextMedium
import com.byrondev.musicplayer.data.models.SearchHistory
import com.byrondev.musicplayer.ui.theme.Pink60
import com.byrondev.musicplayer.ui.theme.Slate80
import com.byrondev.musicplayer.viewModels.MusicViewModels

@OptIn(ExperimentalLayoutApi::class)
@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun SearchHistoryItems(
    searchHistoryItems: State<List<SearchHistory>>,
    query: MutableState<String>,
    active: MutableState<Boolean>,
    musicViewModels: MusicViewModels,

    ) {

    Spacer(modifier = Modifier.height(10.dp))
    Row(
    ) {
        TextMedium(
            "Borrar historial",
            color = Pink60,
            modifier = Modifier
                .background(Slate80, shape = RoundedCornerShape(10.dp))
                .padding(10.dp)
                .clickable {
                    musicViewModels.deleteAllSearchHistory()
                })
    }
    Spacer(modifier = Modifier.height(10.dp))


    if (searchHistoryItems.value.isNotEmpty()) {
        FlowRow(
            modifier = Modifier

                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp), // Espacio entre elementos horizontalmente
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            searchHistoryItems.value.forEach {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Slate80,

                        ),

                    ) {
                    Row(
                        modifier = Modifier.padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        IconSmall(R.drawable.cancel_50) {
                            musicViewModels.deleteSearchHistory(it.id)
                        }
                        TextMedium(it.name, modifier = Modifier.clickable {
                            query.value = it.name
                           // active.value = true
                        })
                    }
                }
            }
        }
    }
}