package com.telda.movieapp.features.home.data.source.remote

import com.telda.movieapp.features.home.domain.model.CreditsResponse
import com.telda.movieapp.features.home.domain.model.MovieDetails
import com.telda.movieapp.features.home.domain.model.PopularMoviesResponse
import com.telda.movieapp.features.home.domain.payload.SearchPayload
import com.telda.movieapp.utils.Resource

interface HomeRemoteDataSource {
    suspend fun getPopularMovies(page: Int): Resource<PopularMoviesResponse?>
    suspend fun searchMovies(payload: SearchPayload): Resource<PopularMoviesResponse?>
    suspend fun getMovieDetails(id: Int): Resource<MovieDetails?>
    suspend fun getMoviesSimilar(id: Int): Resource<PopularMoviesResponse?>
    suspend fun getMovieCredits(id: Int): Resource<CreditsResponse?>
}