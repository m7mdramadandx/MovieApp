package com.telda.movieapp.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.telda.movieapp.utils.horizontalItemXsmallPadding
import com.telda.movieapp.utils.verticalItemXsmallPadding


@Composable
fun MyCircularProgressIndicator(modifier: Modifier = Modifier, size: Dp = 36.dp) {
    CircularProgressIndicator(
        modifier = modifier
            .verticalItemXsmallPadding()
            .horizontalItemXsmallPadding()
            .width(size)
            .height(size),
        color = MaterialTheme.colorScheme.primary
    )
}