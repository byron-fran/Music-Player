package com.byrondev.musicplayer.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CurrentIndexPlaylist(
    private val context : Context
) {

    companion object {
        val Context.dataStore :DataStore<Preferences> by preferencesDataStore("Audio_Current_Index")
        val CURRENT_INDEX_PLAYLIST = intPreferencesKey("current_index_playlist")
    }

    val getCurrentIndexPlaylist : Flow<Int> = context.dataStore.data.map { preferences ->
        preferences[CURRENT_INDEX_PLAYLIST] ?: -1
    }

    suspend fun setCurrentIndexPlaylist(value : Int) {
        context.dataStore.edit {
            it[CURRENT_INDEX_PLAYLIST] = value
        }
    }
}