package com.telda.movieapp.features.home.data.source.local

import kotlinx.coroutines.flow.Flow

interface HomeLocalDataSource {
    suspend fun toggleFavoriteMovie(id: Int)
    suspend fun getFavoriteMoviesIds(): Flow<List<Int>>
}