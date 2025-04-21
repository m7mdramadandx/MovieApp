package com.telda.movieapp.features.home.data.source.local

import com.telda.movieapp.local_storage.DataStoreManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeLocalDataSourceImpl @Inject constructor(
    private val dataStoreManager: DataStoreManager,
) : HomeLocalDataSource {

    override suspend fun toggleFavoriteMovie(id: Int) =
        withContext(Dispatchers.IO) {
            dataStoreManager.addRemoveFavoriteMovie(id)
        }

    override suspend fun getFavoriteMoviesIds(): Flow<List<Int>> =
        withContext(Dispatchers.IO) {
            dataStoreManager.getFavoriteMovies().map { it.toList() }.filterNotNull()
        }
}