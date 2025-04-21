package com.telda.movieapp.features.home.domain.use_case

import com.telda.movieapp.features.home.domain.model.MovieDetails
import com.telda.movieapp.features.home.domain.repo.HomeRepo
import com.telda.movieapp.utils.Resource
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val homeRepo: HomeRepo
) {

    suspend fun execute(page: Int): Resource<MovieDetails> {
        val response = homeRepo.getMovieDetails(page)

        return if (response.data != null) {
            Resource.success(response.data!!)
        } else {
            throw Throwable(message = response.message)
        }

    }

}