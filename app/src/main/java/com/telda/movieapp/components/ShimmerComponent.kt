package com.telda.movieapp.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ShapeDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.telda.movieapp.R
import com.telda.movieapp.utils.shimmerBackground

@Composable
fun ShimmerComponent(height: Dp = 90.dp) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .padding(dimensionResource(id = R.dimen.padding_xsmall))
            .shimmerBackground()
    ) {
        MyCard(
            modifier = Modifier,
            color = Color.Transparent,
            shape = ShapeDefaults.ExtraSmall
        ) {
        }
    }
}

@Preview
@Composable
fun ShimmerComponentPreview() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .padding(dimensionResource(id = R.dimen.padding_xsmall))
            .shimmerBackground()
    ) {
        MyCard(
            modifier = Modifier,
            color = Color.Transparent
        ) {
        }
    }
}
