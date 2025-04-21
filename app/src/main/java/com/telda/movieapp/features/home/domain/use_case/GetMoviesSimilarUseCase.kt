package com.telda.movieapp.features.home.domain.use_case

import com.telda.movieapp.features.home.domain.model.PopularMoviesResponse
import com.telda.movieapp.features.home.domain.repo.HomeRepo
import com.telda.movieapp.utils.Resource
import javax.inject.Inject

class GetMoviesSimilarUseCase @Inject constructor(
    private val homeRepo: HomeRepo
) {

    suspend fun execute(id: Int): Resource<PopularMoviesResponse> {
        val response = homeRepo.getMoviesSimilar(id)

        return if (response.data != null) {
            Resource.success(response.data!!)
        } else {
            throw Throwable(message = response.message)
        }

    }

}