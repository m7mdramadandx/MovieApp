package com.telda.movieapp.features.home.domain.use_case

import com.telda.movieapp.features.home.domain.repo.HomeRepo
import javax.inject.Inject

class ToggleFavoriteMovieUseCase @Inject constructor(
    private val homeRepo: HomeRepo
) {

    suspend fun execute(id: Int) =
        homeRepo.toggleFavoriteMovie(id)

}