package com.telda.movieapp.features.home.domain.use_case

import com.telda.movieapp.features.home.domain.model.PopularMoviesResponse
import com.telda.movieapp.features.home.domain.payload.SearchPayload
import com.telda.movieapp.features.home.domain.repo.HomeRepo
import com.telda.movieapp.utils.Resource
import javax.inject.Inject

class SearchMoviesUseCase @Inject constructor(
    private val homeRepo: HomeRepo
) {

    suspend fun execute(payload: SearchPayload): Resource<PopularMoviesResponse> {
        val response = homeRepo.searchMovies(payload)

        return if (response.data != null) {
            Resource.success(response.data!!)
        } else {
            throw Throwable(message = response.message)
        }

    }

}