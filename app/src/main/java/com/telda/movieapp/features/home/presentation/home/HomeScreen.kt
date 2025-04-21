package com.telda.movieapp.features.home.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.telda.movieapp.components.MyAsyncImage
import com.telda.movieapp.components.MySearchBar2
import com.telda.movieapp.components.MyText
import com.telda.movieapp.components.ShimmerComponent
import com.telda.movieapp.features.home.presentation.home.components.MovieListItem
import com.telda.movieapp.features.home.presentation.home.components.MovieListItemShimmer
import com.telda.movieapp.utils.horizontalItemPadding
import com.telda.movieapp.utils.isFailureState
import com.telda.movieapp.utils.isLoadingState
import com.telda.movieapp.utils.isSuccessDataState
import com.telda.movieapp.utils.noRippleClickable
import com.telda.movieapp.utils.topItemPadding
import com.telda.movieapp.utils.verticalItemPadding

@Composable
fun HomeScreen(
    onNavigateToServiceDetails: (Int) -> Unit,
) {

    val viewModel: HomeViewModel = hiltViewModel()
    val lazyColumnListState = rememberLazyListState()
    val searchResultState = viewModel.searchResultState
    val popularMoviesState = viewModel.popularMoviesState
    val shouldStartPaginate = remember {
        derivedStateOf {
            viewModel.canPaginate && (lazyColumnListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
                ?: -9) >= (lazyColumnListState.layoutInfo.totalItemsCount - 6)
        }
    }

    LaunchedEffect(key1 = shouldStartPaginate.value) {
        if (shouldStartPaginate.value)
            viewModel.getPopularMovies()
    }


    LaunchedEffect(searchResultState.searchKeyword) {
        if (searchResultState.searchKeyword.length > 2)
            viewModel.searchMovies()
    }

    Box(
        Modifier
            .fillMaxSize()
            .semantics { isTraversalGroup = true }) {

        MySearchBar2(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .semantics { traversalIndex = 0f },
            query = searchResultState.searchKeyword,
            placeholderText = "Search by movie title",
            onQueryChange = { viewModel.searchMovies(it) },
            content = {
                Column(
                    Modifier
                        .fillMaxHeight()
                        .verticalScroll(rememberScrollState()),
                ) {
                    if (searchResultState.searchKeyword.isEmpty()) {
                        Spacer(modifier = Modifier.topItemPadding())
                        MyText(
                            text = "Enter movie title",
                            modifier = Modifier.horizontalItemPadding()
                        )
                    } else if (searchResultState.isLoading) {
                        repeat(12) {
                            ShimmerComponent(height = 50.dp)
                        }
                    } else if (searchResultState.items?.isEmpty() == true) {
                        Spacer(modifier = Modifier.topItemPadding())
                        MyText(
                            text = "No results found",
                            modifier = Modifier.horizontalItemPadding()
                        )
                    } else {
                        searchResultState.items?.forEachIndexed { index, movie ->
                            if (index == 0) Spacer(modifier = Modifier.topItemPadding())
                            Row(
                                modifier = Modifier
                                    .verticalItemPadding()
                                    .horizontalItemPadding()
                                    .noRippleClickable {
                                        movie.id?.let { onNavigateToServiceDetails(it) }
                                    },
                                verticalAlignment = Alignment.CenterVertically

                            ) {
                                MyAsyncImage(
                                    imageUrl = "https://image.tmdb.org/t/p/w185${movie.posterPath}",
                                    modifier = Modifier
                                        .size(50.dp)
                                        .padding(end = 8.dp),
                                    contentScale = ContentScale.Crop,
                                )

                                MyText(
                                    text = movie.title,
                                    modifier = Modifier
                                        .verticalItemPadding()
                                )
                            }
                            HorizontalDivider()
                        }
                    }
                }
            },
        )

        LazyColumn(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 90.dp)
                .fillMaxSize(),
            state = lazyColumnListState
        ) {

            when {
                isLoadingState(popularMoviesState) -> {
                    items(10) {
                        MovieListItemShimmer()
                    }
                }

                isFailureState(popularMoviesState) -> {
                    item {
                        Box(modifier = Modifier.fillMaxSize()) {
                            MyText(popularMoviesState.error)
                        }
                    }
                }

                isSuccessDataState(popularMoviesState) -> {
                    itemsIndexed(popularMoviesState.items ?: emptyList()) { index, movie ->
                        MovieListItem(
                            movie = movie,
                            onMovieClicked = { movie.id?.let { onNavigateToServiceDetails.invoke(it) } },
                            onAddToWatchlistClicked = {
                                viewModel.toggleFavoriteMovie(movie.id)
                            }
                        )
                    }

                    item {
                        if (popularMoviesState.isLoadingMore) {
                            MovieListItemShimmer()
                        }
                    }
                }
            }
        }
    }
}