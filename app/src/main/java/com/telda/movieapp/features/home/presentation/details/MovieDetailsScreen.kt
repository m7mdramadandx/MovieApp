package com.telda.movieapp.features.home.presentation.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.telda.movieapp.components.MyCircularProgressIndicator
import com.telda.movieapp.components.MyText
import com.telda.movieapp.features.home.presentation.details.components.CastList
import com.telda.movieapp.features.home.presentation.details.components.MovieDetailsSection
import com.telda.movieapp.features.home.presentation.details.components.SimilarMovieItem
import com.telda.movieapp.utils.isFailureState
import com.telda.movieapp.utils.isLoadingState
import com.telda.movieapp.utils.isSuccessDataStateForObject

@Composable
fun MovieDetailScreen(
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val viewModel: MovieDetailsViewModel = hiltViewModel()
    val lazyColumnListState = rememberLazyListState()
    val screenState = viewModel.movieDetailsState
    val similarMoviesState = viewModel.moviesSimilarState
    val movieCreditsState = viewModel.movieCreditsState


    when {
        isLoadingState(screenState) -> {
            Box(modifier = Modifier.fillMaxSize()) {
                MyCircularProgressIndicator(Modifier.align(Alignment.Center))
            }
        }

        isFailureState(screenState) -> {
            Box(modifier = Modifier.fillMaxSize()) {
                MyText(screenState.error)
            }
        }

        isSuccessDataStateForObject(screenState) -> {

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                state = lazyColumnListState
            ) {
                // Movie Details Section
                item {
                    MovieDetailsSection(
                        movieDetails = screenState.item,
                        onAddToWatchlistClicked = {
                            viewModel.toggleFavoriteMovie()
                        }
                    )
                }

                // Similar Movies Section
                if (similarMoviesState.items?.isNotEmpty() == true) {
                    item {
                        MyText(
                            text = "Similar Movies",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
                        )
                    }
                    items(similarMoviesState.items) { movie ->
                        SimilarMovieItem(movie = movie)
                    }
                }

                // Cast Section
                if (movieCreditsState.item != null) {
                    item {
                        CastList(cast = movieCreditsState.item)
                    }
                }
            }
        }
    }
}
