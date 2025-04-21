package com.telda.movieapp.local_storage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class DataStoreManager @Inject constructor(@ApplicationContext var context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("telda_prefs")
    private val movieIdsKey: Preferences.Key<String> = stringPreferencesKey("movieIds")

    suspend fun addRemoveFavoriteMovie(id: Int) {
        context.dataStore.edit { preferences ->
            val currentIds = preferences[movieIdsKey]?.split(",")?.mapNotNull { it.toIntOrNull() }
                ?.toMutableSet() ?: mutableSetOf()

            if (currentIds.contains(id)) {
                currentIds.remove(id)
            } else {
                currentIds.add(id)
            }

            preferences[movieIdsKey] = currentIds.joinToString(",")
        }
    }

    fun getFavoriteMovies(): Flow<List<Int>> =
        context.dataStore.data.map { preferences ->
            preferences[movieIdsKey]?.split(",")?.mapNotNull { it.toIntOrNull() }?.toSet()?.toList()
                ?: emptyList()
        }

}