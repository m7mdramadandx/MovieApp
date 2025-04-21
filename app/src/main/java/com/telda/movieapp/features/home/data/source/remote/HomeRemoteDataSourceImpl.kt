package com.telda.movieapp.features.home.data.source.remote

import com.telda.movieapp.core.ApiService
import com.telda.movieapp.features.home.domain.model.CreditsResponse
import com.telda.movieapp.features.home.domain.model.MovieDetails
import com.telda.movieapp.features.home.domain.model.PopularMoviesResponse
import com.telda.movieapp.features.home.domain.payload.SearchPayload
import com.telda.movieapp.utils.Constants
import com.telda.movieapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeRemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService,
) : HomeRemoteDataSource {

    private fun getBearerAuthHeaders(): Map<String, String> {
        val basicAuth =
            "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiODUxMjZiMTExMTBhZDQ3NzUyNmU1MWNlNmIzNjFiNiIsIm5iZiI6MTYxNzMwODYyMC45MzcsInN1YiI6IjYwNjYyYmNjZWJiOTlkMDA3MDI0ZTFlNyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.xtL2ji-DTQMdAPFxDAK4n80x5kFwn2ofwkzYFPbvwiY"
        return mapOf("Authorization" to basicAuth)
    }


    override suspend fun getPopularMovies(page: Int): Resource<PopularMoviesResponse?> {
        val tokenAuthHeaders = getBearerAuthHeaders()
        val response = apiService.getPopularMovies(
            headers = Constants.headers.plus(tokenAuthHeaders),
            page = page
        )
        if (response.isSuccessful) {
            return Resource.success(response.body())
        } else {
            val errorBody = response.errorBody()?.string()
            return Resource.error(errorBody.toString())
        }
    }

    override suspend fun searchMovies(payload: SearchPayload): Resource<PopularMoviesResponse?> =
        withContext(Dispatchers.IO) {
            val tokenAuthHeaders = getBearerAuthHeaders()

            val response = apiService.searchMovies(
                headers = Constants.headers.plus(tokenAuthHeaders),
                query = payload.query,
                page = payload.page
            )

            if (response.isSuccessful) {
                Resource.success(response.body())
            } else {
                val errorBody = response.errorBody()?.string()
                Resource.error(errorBody.toString())

            }
        }


    override suspend fun getMovieDetails(id: Int): Resource<MovieDetails?> =
        withContext(Dispatchers.IO) {
            val tokenAuthHeaders = getBearerAuthHeaders()

            val response = apiService.getMovieDetails(
                headers = Constants.headers.plus(tokenAuthHeaders),
                movieId = id
            )

            if (response.isSuccessful) {
                Resource.success(response.body())
            } else {
                val errorBody = response.errorBody()?.string()
                Resource.error(errorBody.toString())

            }
        }

    override suspend fun getMoviesSimilar(id: Int): Resource<PopularMoviesResponse?> =
        withContext(Dispatchers.IO) {
            val tokenAuthHeaders = getBearerAuthHeaders()
            val response = apiService.getMovieSimilar(
                headers = Constants.headers.plus(tokenAuthHeaders),
                movieId = id
            )

            if (response.isSuccessful) {
                Resource.success(response.body())
            } else {
                val errorBody = response.errorBody()?.string()
                Resource.error(errorBody.toString())
            }
        }

    override suspend fun getMovieCredits(id: Int): Resource<CreditsResponse?> =
        withContext(Dispatchers.IO) {
            val tokenAuthHeaders = getBearerAuthHeaders()
            val response = apiService.getMovieCredits(
                headers = Constants.headers.plus(tokenAuthHeaders),
                movieId = id
            )
            if (response.isSuccessful) {
                Resource.success(response.body())
            } else {
                val errorBody = response.errorBody()?.string()
                Resource.error(errorBody.toString())
            }
        }

}