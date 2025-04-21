package com.telda.movieapp.features.home.presentation.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.telda.movieapp.core.BaseViewModel
import com.telda.movieapp.features.home.domain.model.CreditsResponse
import com.telda.movieapp.features.home.domain.model.Movie
import com.telda.movieapp.features.home.domain.model.MovieDetails
import com.telda.movieapp.features.home.domain.use_case.GetMovieCreditsUseCase
import com.telda.movieapp.features.home.domain.use_case.GetMovieDetailsUseCase
import com.telda.movieapp.features.home.domain.use_case.GetMoviesFavoriteIdsUseCase
import com.telda.movieapp.features.home.domain.use_case.GetMoviesSimilarUseCase
import com.telda.movieapp.features.home.domain.use_case.ToggleFavoriteMovieUseCase
import com.telda.movieapp.utils.Constants
import com.telda.movieapp.utils.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val getMoviesSimilarUseCase: GetMoviesSimilarUseCase,
    private val getMovieCreditsUseCase: GetMovieCreditsUseCase,
    private val getMoviesFavoriteIdsUseCase: GetMoviesFavoriteIdsUseCase,
    private val toggleFavoriteMovieUseCase: ToggleFavoriteMovieUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    var movieDetailsState by mutableStateOf(ScreenState<MovieDetails>())
    var moviesSimilarState by mutableStateOf(ScreenState<Movie>())
    var movieCreditsState by mutableStateOf(ScreenState<CreditsResponse>())
    // Use StateFlow instead of mutableStateList
    private val _favoriteMoviesIds = MutableStateFlow<List<Int>>(emptyList())


    private var movieId by mutableIntStateOf(0)

    init {
        movieId = savedStateHandle.get<Int>(Constants.MOVIE_ID) ?: 0
        getFavoriteMoviesIds()
        getMovieDetails()
    }

    private fun getMovieDetails() {
        movieDetailsState = movieDetailsState.copy(isLoading = true)
        viewModelScope.launchCatching(
            block = {
                val response = getMovieDetailsUseCase.execute(movieId)
                getMoviesSimilar()
                getMovieCredits()
                movieDetailsState = movieDetailsState.copy(
                    isLoading = false,
                    item = response.data?.copy(isFavorite =  movieId in _favoriteMoviesIds.value)
                )
            }, onError = {
                movieDetailsState = movieDetailsState.copy(
                    isLoading = false,
                    error = it.message ?: "Something went wrong"
                )
            }
        )
    }

    private fun getMoviesSimilar() {
        moviesSimilarState = moviesSimilarState.copy(isLoading = true)
        viewModelScope.launchCatching(
            block = {
                val response = getMoviesSimilarUseCase.execute(movieId)
                moviesSimilarState = moviesSimilarState.copy(
                    isLoading = false,
                    items = response.data?.movies?.filterNotNull()?.take(5) ?: emptyList()
                )

            },
            onError = {
                moviesSimilarState = moviesSimilarState.copy(
                    isLoading = false,
                    error = it.message ?: "Something went wrong"
                )
            })
    }

    private fun getMovieCredits() {
        movieCreditsState = movieCreditsState.copy(isLoading = true)
        viewModelScope.launchCatching(
            block = {
                val response = getMovieCreditsUseCase.execute(movieId)
                movieCreditsState = movieCreditsState.copy(
                    isLoading = false,
                    item = response.data
                )
            },
            onError = {
                movieCreditsState = movieCreditsState.copy(
                    isLoading = false,
                    error = it.message ?: "Something went wrong"
                )
            })
    }

    fun toggleFavoriteMovie() {
        movieDetailsState = movieDetailsState.copy(
            item = movieDetailsState.item?.copy(
                isFavorite = !((movieDetailsState.item?.isFavorite) ?: false)
            )
        )
        viewModelScope.launchCatching(
            block = {
                toggleFavoriteMovieUseCase.execute(movieId)
            },
            onError = {},
        )
    }

    private fun getFavoriteMoviesIds() {
        viewModelScope.launchCatching(
            block = {
                getMoviesFavoriteIdsUseCase.execute().collectLatest {
                    _favoriteMoviesIds.value = it
                }
            }, onError = {}
        )
    }
}