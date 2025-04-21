package com.telda.movieapp.features.home.data.repo

import com.telda.movieapp.features.home.data.source.local.HomeLocalDataSource
import com.telda.movieapp.features.home.data.source.remote.HomeRemoteDataSource
import com.telda.movieapp.features.home.domain.model.CreditsResponse
import com.telda.movieapp.features.home.domain.model.MovieDetails
import com.telda.movieapp.features.home.domain.model.PopularMoviesResponse
import com.telda.movieapp.features.home.domain.payload.SearchPayload
import com.telda.movieapp.features.home.domain.repo.HomeRepo
import com.telda.movieapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class HomeRepoImpl @Inject constructor(
    private val remoteDataSource: HomeRemoteDataSource,
    private val localDataSource: HomeLocalDataSource,
) : HomeRepo {

    override suspend fun getPopularMovies(page: Int): Resource<PopularMoviesResponse?> =
        remoteDataSource.getPopularMovies(page)

    override suspend fun searchMovies(payload: SearchPayload): Resource<PopularMoviesResponse?> =
        remoteDataSource.searchMovies(payload)

    override suspend fun getMovieDetails(id: Int): Resource<MovieDetails?> =
        remoteDataSource.getMovieDetails(id)

    override suspend fun getMoviesSimilar(id: Int): Resource<PopularMoviesResponse?> =
        remoteDataSource.getMoviesSimilar(id)

    override suspend fun getMovieCredits(id: Int): Resource<CreditsResponse?> =
        remoteDataSource.getMovieCredits(id)

    override suspend fun toggleFavoriteMovie(id: Int) =
        localDataSource.toggleFavoriteMovie(id)

    override suspend fun getFavoriteMoviesIds(): Flow<List<Int>> =
        localDataSource.getFavoriteMoviesIds()

}