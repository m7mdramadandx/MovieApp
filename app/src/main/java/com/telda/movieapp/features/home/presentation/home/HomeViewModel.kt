package com.telda.movieapp.features.home.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.telda.movieapp.core.BaseViewModel
import com.telda.movieapp.features.home.domain.model.Movie
import com.telda.movieapp.features.home.domain.payload.SearchPayload
import com.telda.movieapp.features.home.domain.use_case.GetMoviesFavoriteIdsUseCase
import com.telda.movieapp.features.home.domain.use_case.GetPopularMoviesUseCase
import com.telda.movieapp.features.home.domain.use_case.SearchMoviesUseCase
import com.telda.movieapp.features.home.domain.use_case.ToggleFavoriteMovieUseCase
import com.telda.movieapp.utils.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val searchMoviesUseCase: SearchMoviesUseCase,
    private val getMoviesFavoriteIdsUseCase: GetMoviesFavoriteIdsUseCase,
    private val toggleFavoriteMovieUseCase: ToggleFavoriteMovieUseCase,
) : BaseViewModel() {

    var searchResultState by mutableStateOf(ScreenState<Movie>())
    var popularMoviesState by mutableStateOf(ScreenState<Movie>())
    private var allMovies = mutableListOf<Movie>()

    private var searchPayload by mutableStateOf(SearchPayload.defaults)
    private var page by mutableIntStateOf(0)
    var canPaginate by mutableStateOf(false)
    private val _favoriteMoviesIds = MutableStateFlow<List<Int>>(emptyList())

    init {
        getFavoriteMoviesIds()
        getPopularMovies()
    }

    fun getPopularMovies() {
        if (popularMoviesState.items.isNullOrEmpty()) {
            page = 1
            popularMoviesState = popularMoviesState.copy(isLoading = true)
        } else {
            page += 1
            popularMoviesState = popularMoviesState.copy(isLoadingMore = true)
        }

        if (page == 1)
            popularMoviesState = popularMoviesState.copy(isLoading = true)
        else if (page > 2)
            popularMoviesState = popularMoviesState.copy(isLoadingMore = true)

        viewModelScope.launchCatching(
            block = {
                delay(2000) //adding delay to view loader
                val response = getPopularMoviesUseCase.execute(page)
                response.data?.let {
                    canPaginate = page <= (it.totalPages ?: 0)
                    it.movies?.filterNotNull()?.forEach { movie ->
                        movie.isFavorite = movie.id in _favoriteMoviesIds.value
                        allMovies.add(movie)
                    }

                    popularMoviesState = popularMoviesState.copy(
                        isLoading = false,
                        isLoadingMore = false,
                        items = allMovies
                    )
                }
            }, onError = {
                popularMoviesState = popularMoviesState.copy(
                    isLoading = false,
                    isLoadingMore = false,
                    error = it.message ?: "Something went wrong"
                )
            }
        )

    }

    fun searchMovies(query: String) {
        searchResultState = searchResultState.copy(searchKeyword = query)
        searchPayload = searchPayload.copy(query = query)
    }

    fun searchMovies() {
        viewModelScope.launchCatching(
            block = {
                searchResultState = searchResultState.copy(isLoading = true)

                val response = searchMoviesUseCase.execute(searchPayload)
                response.data?.let {
//                    canPaginate = it.movies?.size == 10
//                    it.movies?.forEach {
//                        if (it != null) allMovies.add(it)
//                    }
                    it.movies?.filterNotNull()?.let {
                        searchResultState = searchResultState.copy(
                            isLoading = false,
                            items = it
                        )
                    }
                }
            }, onError = {
                searchResultState = searchResultState.copy(
                    isLoading = false,
                    error = it.message ?: "Something went wrong"
                )
            }
        )
    }

    fun toggleFavoriteMovie(id: Int?) {
        popularMoviesState = popularMoviesState.copy(
            items = popularMoviesState.items?.map {
                if (it.id == id) it.copy(isFavorite = !(it.isFavorite ?: false)) else it
            }
        )

        viewModelScope.launchCatching(
            block = {
                id?.let { toggleFavoriteMovieUseCase.execute(it) }
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
            }, {}
        )
    }

}