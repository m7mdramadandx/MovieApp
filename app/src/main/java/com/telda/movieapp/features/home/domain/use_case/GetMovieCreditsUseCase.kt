package com.telda.movieapp.features.home.domain.use_case

import com.telda.movieapp.features.home.domain.model.CreditsResponse
import com.telda.movieapp.features.home.domain.model.MovieDetails
import com.telda.movieapp.features.home.domain.repo.HomeRepo
import com.telda.movieapp.utils.Resource
import javax.inject.Inject

class GetMovieCreditsUseCase @Inject constructor(
    private val homeRepo: HomeRepo
) {

    suspend fun execute(id: Int): Resource<CreditsResponse> {
        val response = homeRepo.getMovieCredits(id)

        return if (response.data != null) {
            Resource.success(response.data!!)
        } else {
            throw Throwable(message = response.message)
        }

    }

}