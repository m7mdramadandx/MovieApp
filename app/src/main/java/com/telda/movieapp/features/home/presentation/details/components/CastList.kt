package com.telda.movieapp.features.home.presentation.details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.telda.movieapp.R
import com.telda.movieapp.components.MyAsyncImage
import com.telda.movieapp.components.MyCard
import com.telda.movieapp.components.MyText
import com.telda.movieapp.features.home.domain.model.Cast
import com.telda.movieapp.features.home.domain.model.CreditsResponse
import com.telda.movieapp.features.home.domain.model.Crew

@Composable
fun CastList(
    cast: CreditsResponse,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        // Actors Section
        val actors = cast.cast
            ?.filterNotNull()
            ?.filter { it.knownForDepartment == "Acting" }
            ?.sortedByDescending { it.popularity ?: 0.0 }
            ?.take(if (cast.cast.size > 5) 5 else cast.cast.size)
            ?: emptyList()

        if (actors.isNotEmpty()) {
            MyText(
                text = "Cast",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
            )

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                items(actors) { actor ->
                    CastMemberItem(cast = actor)
                }
            }
        }

        // Directors Section
        val directors = cast.crew
            ?.filterNotNull()
            ?.filter { it.department == "Directing" }
            ?.sortedByDescending { it.popularity ?: 0.0 }
            ?.take(if (cast.crew.size > 5) 5 else cast.crew.size)
            ?: emptyList()

        if (directors.isNotEmpty()) {
            MyText(
                text = "Directors",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                items(directors) { director ->
                    CrewMemberItem(crew = director)
                }
            }
        }

    }
}

@Composable
fun CrewMemberItem(
    crew: Crew,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .width(100.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MyCard(
            shape = CircleShape,
            modifier = Modifier.size(80.dp)
        ) {
            MyAsyncImage(
                imageUrl = "https://image.tmdb.org/t/p/w185${crew.profilePath}",
                errorPainter = R.drawable.ic_person_24,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        MyText(
            text = crew.name ?: "Unknown",
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Medium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        crew.job?.let {
            MyText(
                text = it,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun CastMemberItem(
    cast: Cast,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .width(100.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MyCard(
            shape = CircleShape,
            modifier = Modifier.size(80.dp)
        ) {
            MyAsyncImage(
                imageUrl = "https://image.tmdb.org/t/p/w185${cast.profilePath}",
                errorPainter = R.drawable.ic_person_24,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        MyText(
            text = cast.name ?: "Unknown",
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Medium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        cast.character?.let {
            MyText(
                text = it,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}
