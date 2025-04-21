package com.telda.movieapp.features.home.domain.repo

import com.telda.movieapp.features.home.domain.model.CreditsResponse
import com.telda.movieapp.features.home.domain.model.MovieDetails
import com.telda.movieapp.features.home.domain.model.PopularMoviesResponse
import com.telda.movieapp.features.home.domain.payload.SearchPayload
import com.telda.movieapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface HomeRepo {
    // Remote Data Source
    suspend fun getPopularMovies(page: Int): Resource<PopularMoviesResponse?>
    suspend fun searchMovies(payload: SearchPayload): Resource<PopularMoviesResponse?>
    suspend fun getMovieDetails(id: Int): Resource<MovieDetails?>
    suspend fun getMoviesSimilar(id: Int): Resource<PopularMoviesResponse?>
    suspend fun getMovieCredits(id: Int): Resource<CreditsResponse?>

    // Local Data Source
    suspend fun toggleFavoriteMovie(id: Int)
    suspend fun getFavoriteMoviesIds(): Flow<List<Int>>

}