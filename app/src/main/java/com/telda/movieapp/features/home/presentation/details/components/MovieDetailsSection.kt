package com.telda.movieapp.features.home.presentation.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.AttachMoney
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material.icons.outlined.TheaterComedy
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.telda.movieapp.components.MyAsyncImage
import com.telda.movieapp.components.MyText
import com.telda.movieapp.features.home.domain.model.MovieDetails
import com.telda.movieapp.utils.formatDate
import java.text.NumberFormat
import java.util.Locale

@Composable
fun MovieDetailsSection(
    movieDetails: MovieDetails?,
    onAddToWatchlistClicked: () -> Unit
) {

    val isOnWatchlist = movieDetails?.isFavorite ?: false

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        // Movie Poster Backdrop with Gradient Overlay
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .clip(RoundedCornerShape(12.dp))
        ) {
            MyAsyncImage(
                imageUrl = "https://image.tmdb.org/t/p/w780${movieDetails?.backdropPath}",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // Gradient Overlay
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                MaterialTheme.colorScheme.surface.copy(alpha = 0.4f),
                                MaterialTheme.colorScheme.surface
                            ),
                            startY = 0f,
                            endY = Float.POSITIVE_INFINITY
                        )
                    )
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Title and Watchlist Button
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)

        ) {
            MyText(
                text = movieDetails?.title,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )

            IconToggleButton(
                checked = isOnWatchlist,
                onCheckedChange = { onAddToWatchlistClicked() }
            ) {
                Icon(
                    imageVector = if (isOnWatchlist) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = if (isOnWatchlist) "Remove from watchlist" else "Add to watchlist",
                )
            }
        }

        // Tagline
        if (!movieDetails?.tagline.isNullOrEmpty()) {
            MyText(
                text = "\"${movieDetails?.tagline}\"",
                style = MaterialTheme.typography.titleMedium,
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
                modifier = Modifier
                    .padding(top = 4.dp, bottom = 8.dp)
                    .padding(horizontal = 16.dp)
            )
        }

        // Overview
        MyText(
            text = movieDetails?.overview ?: "No overview available.",
            style = MaterialTheme.typography.bodyLarge,
            lineHeight = 24.sp,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
        )

        // Metadata Grid
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 16.dp)
        ) {
            // Release Date and Status
            DetailRow(
                icon = Icons.Outlined.CalendarToday,
                label = "Release Date",
                value = movieDetails?.releaseDate?.formatDate() ?: "N/A"
            )

            // Status and Runtime
            DetailRow(
                icon = Icons.Outlined.Info,
                label = "Status",
                value = movieDetails?.status ?: "N/A"
            )

            // Revenue
            DetailRow(
                icon = Icons.Outlined.AttachMoney,
                label = "Revenue",
                value = movieDetails?.revenue?.formatCurrency() ?: "N/A"
            )

            // Runtime
            DetailRow(
                icon = Icons.Outlined.Schedule,
                label = "Runtime",
                value = movieDetails?.runtime?.formatRuntime() ?: "N/A"
            )

            // Genres
            if (!movieDetails?.genres.isNullOrEmpty()) {
                DetailRow(
                    icon = Icons.Outlined.TheaterComedy,
                    label = "Genres",
                    value = movieDetails?.genres?.map { it?.name }?.joinToString() ?: ""
                )
            }
        }

        // Watchlist Button
        Button(
            onClick = { onAddToWatchlistClicked() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isOnWatchlist) {
                    MaterialTheme.colorScheme.secondaryContainer
                } else {
                    MaterialTheme.colorScheme.primary
                }
            ),
            shape = MaterialTheme.shapes.large
        ) {
            Icon(
                imageVector = if (isOnWatchlist) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                contentDescription = null,
                modifier = Modifier.padding(end = 8.dp)
            )
            MyText(
                text = if (isOnWatchlist) "Remove from Watchlist" else "Add to Watchlist",
                style = MaterialTheme.typography.labelLarge,
                color = if (isOnWatchlist) {
                    MaterialTheme.colorScheme.onPrimary
                } else {
                    MaterialTheme.colorScheme.secondaryContainer
                }
            )
        }
    }
}


@Composable
private fun DetailRow(icon: ImageVector, label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        MyText(
            text = "$label: ",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
        )
        MyText(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
        )
    }
}


fun Int.formatCurrency(): String {
    return NumberFormat.getCurrencyInstance(Locale.US).format(this)
}

fun Int.formatRuntime(): String {
    val hours = this / 60
    val minutes = this % 60
    return if (hours > 0) {
        "${hours}h ${minutes}m"
    } else {
        "${minutes}m"
    }
}