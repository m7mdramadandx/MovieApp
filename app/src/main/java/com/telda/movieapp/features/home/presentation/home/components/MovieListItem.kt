package com.telda.movieapp.features.home.presentation.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.telda.movieapp.components.MyAsyncImage
import com.telda.movieapp.components.MyCard
import com.telda.movieapp.components.MyText
import com.telda.movieapp.features.home.domain.model.Movie

@Composable
fun MovieListItem(
    movie: Movie,
    onMovieClicked: () -> Unit,
    onAddToWatchlistClicked: () -> Unit
) {
    val isOnWatchlist = movie.isFavorite ?: false

    MyCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        elevation = 4.dp,
        shape = MaterialTheme.shapes.medium,
        onClick = onMovieClicked
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Movie Poster
            MyAsyncImage(
                imageUrl = "https://image.tmdb.org/t/p/w185${movie.posterPath}",
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop,
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                // Title and Watchlist Button
                Row(verticalAlignment = Alignment.CenterVertically) {
                    MyText(
                        text = movie.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f)
                    )

                    IconToggleButton(
                        checked = isOnWatchlist,
                        onCheckedChange = { onAddToWatchlistClicked() },
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .size(24.dp)
                    ) {
                        Icon(
                            imageVector = if (isOnWatchlist) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                            contentDescription = null,
                            tint = if (isOnWatchlist) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(
                                alpha = 0.6f
                            )
                        )
                    }
                }

                // Release Year
                movie.releaseDate?.split("-")?.firstOrNull()?.let { year ->
                    MyText(
                        text = year,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                }

                // Overview
                MyText(
                    text = movie.overview ?: "No overview available.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(bottom = 4.dp)
                )

                // Rating
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "Rating",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    MyText(
                        text = "%.1f".format(movie.voteAverage ?: 0f),
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
                    )
                    MyText(
                        text = " (${movie.voteCount ?: 0})",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                }
            }
        }
    }
}