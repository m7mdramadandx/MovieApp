package com.telda.movieapp.features.home.domain.use_case

import com.telda.movieapp.features.home.domain.repo.HomeRepo
import javax.inject.Inject

class GetMoviesFavoriteIdsUseCase @Inject constructor(
    private val homeRepo: HomeRepo
) {

    suspend fun execute() =
        homeRepo.getFavoriteMoviesIds()

}