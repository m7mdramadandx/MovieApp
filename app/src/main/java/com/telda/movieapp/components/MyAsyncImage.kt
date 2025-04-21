package com.telda.movieapp.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import com.telda.movieapp.R
import com.telda.movieapp.utils.shimmerBackground


@Composable
fun MyAsyncImage(
    modifier: Modifier = Modifier,
    imageUrl: String?,
    contentScale: ContentScale = ContentScale.Fit,
    colorFilter: ColorFilter? = null,
    errorPainter: Int = R.drawable.ic_error_img
) {
    SubcomposeAsyncImage(
        modifier = modifier,
        contentDescription = "",
        colorFilter = colorFilter,
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl ?: "")
            .crossfade(true)
            .build(),
        contentScale = contentScale,
        loading = {
            this.SubcomposeAsyncImageContent(
                modifier = Modifier.shimmerBackground()
            )
        },
        error = {
            this.SubcomposeAsyncImageContent(
                painter = painterResource(id = errorPainter)
            )
        }
    )
}
