package com.telda.movieapp.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun MyCard(
    modifier: Modifier = Modifier,
    shape: Shape = CardDefaults.shape,
    color: Color = MaterialTheme.colorScheme.surfaceContainerLow,
    elevation: Dp = if (isSystemInDarkTheme()) 0.dp else 12.dp,
    border: BorderStroke? = null,
    onClick: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier
            .clickable(onClick != null) { onClick?.invoke() },
        colors = CardDefaults.elevatedCardColors(
            containerColor = color,
        ),
        elevation = CardDefaults.cardElevation(elevation),
        border = border,
        shape = shape,
        content = { content.invoke() }
    )
}
