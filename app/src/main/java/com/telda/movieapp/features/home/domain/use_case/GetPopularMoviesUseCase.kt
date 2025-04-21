package com.telda.movieapp.features.home.domain.use_case

import com.telda.movieapp.features.home.domain.model.Movie
import com.telda.movieapp.features.home.domain.model.PopularMoviesResponse
import com.telda.movieapp.features.home.domain.repo.HomeRepo
import com.telda.movieapp.utils.Resource
import java.util.SortedMap
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val homeRepo: HomeRepo
) {

    suspend fun execute(page: Int): Resource<PopularMoviesResponse> {
        val response = homeRepo.getPopularMovies(page)

        return if (response.data != null) {
            val popularMoviesResponse = response.data
            val groupedByYear: SortedMap<String, List<Movie?>>? = popularMoviesResponse?.movies?.groupBy { movie ->
                movie?.releaseDate?.split("-")?.firstOrNull() ?: "Unknown"
            }?.toSortedMap(compareByDescending { it })

            val sortedMoviesList: List<Movie> = groupedByYear?.flatMap { (_, movies) ->
                movies.filterNotNull().sortedBy { it.releaseDate }
            } ?: emptyList()

            Resource.success(response.data!!.copy(movies = sortedMoviesList))
        } else {
            throw Throwable(message = response.message)
        }

    }

}