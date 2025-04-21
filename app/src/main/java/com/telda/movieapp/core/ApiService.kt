package com.telda.movieapp.core

import com.telda.movieapp.features.home.domain.model.CreditsResponse
import com.telda.movieapp.features.home.domain.model.MovieDetails
import com.telda.movieapp.features.home.domain.model.PopularMoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular?")
    suspend fun getPopularMovies(
        @HeaderMap headers: Map<String, String>,
        @Query("page") page: Int,
    ): Response<PopularMoviesResponse>

    @GET("search/movie?")
    suspend fun searchMovies(
        @HeaderMap headers: Map<String, String>,
        @Query("query") query: String,
        @Query("page") page: Int,
    ): Response<PopularMoviesResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @HeaderMap headers: Map<String, String>,
        @Path("movie_id") movieId: Int,
    ): Response<MovieDetails>

    @GET("movie/{movie_id}/similar")
    suspend fun getMovieSimilar(
        @HeaderMap headers: Map<String, String>,
        @Path("movie_id") movieId: Int,
    ): Response<PopularMoviesResponse>

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(
        @HeaderMap headers: Map<String, String>,
        @Path("movie_id") movieId: Int,
    ): Response<CreditsResponse>



}