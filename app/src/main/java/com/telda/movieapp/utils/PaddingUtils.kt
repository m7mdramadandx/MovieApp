package com.telda.movieapp.utils

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.telda.movieapp.R

/////////////////// Screen Padding ///////////////////

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.fullScreenPadding() =
    composed {
        padding(
            vertical = dimensionResource(id = R.dimen.padding_medium),
            horizontal = dimensionResource(id = R.dimen.padding_large)
        )
    }

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.horizontalScreenPadding() =
    composed { padding(horizontal = dimensionResource(id = R.dimen.padding_large)) }

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.topScreenPadding() =
    composed { padding(top = dimensionResource(id = R.dimen.padding_medium)) }

/////////////////// XXX-Small Padding ///////////////////

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.verticalItemXXXsmallPadding() =
    composed { padding(vertical = dimensionResource(id = R.dimen.padding_xxxsmall)) }

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.horizontalItemXXXsmallPadding() =
    composed { padding(horizontal = dimensionResource(id = R.dimen.padding_xxxsmall)) }

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.topItemXXXsmallPadding() =
    composed { padding(top = dimensionResource(id = R.dimen.padding_xxxsmall)) }

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.bottomItemXXXsmallPadding() =
    composed { padding(bottom = dimensionResource(id = R.dimen.padding_xxxsmall)) }

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.startItemXXXsmallPadding() =
    composed { padding(start = dimensionResource(id = R.dimen.padding_xxxsmall)) }

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.endIItemXXXsmallPadding() =
    composed { padding(end = dimensionResource(id = R.dimen.padding_xxxsmall)) }

/////////////////// XX-Small Padding ///////////////////

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.verticalItemXXsmallPadding() =
    composed { padding(vertical = dimensionResource(id = R.dimen.padding_xxsmall)) }

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.horizontalItemXXsmallPadding() =
    composed { padding(horizontal = dimensionResource(id = R.dimen.padding_xxsmall)) }

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.topItemXXsmallPadding() =
    composed { padding(top = dimensionResource(id = R.dimen.padding_xxsmall)) }

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.bottomItemXXsmallPadding() =
    composed { padding(bottom = dimensionResource(id = R.dimen.padding_xxsmall)) }

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.startItemXXsmallPadding() =
    composed { padding(start = dimensionResource(id = R.dimen.padding_xxsmall)) }

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.endIItemXXsmallPadding() =
    composed { padding(end = dimensionResource(id = R.dimen.padding_xxsmall)) }

/////////////////// X-Small Padding ///////////////////

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.verticalItemXsmallPadding() =
    composed { padding(vertical = dimensionResource(id = R.dimen.padding_xsmall)) }

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.horizontalItemXsmallPadding() =
    composed { padding(horizontal = dimensionResource(id = R.dimen.padding_xsmall)) }

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.topItemXsmallPadding() =
    composed { padding(top = dimensionResource(id = R.dimen.padding_xsmall)) }

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.bottomItemXsmallPadding() =
    composed { padding(bottom = dimensionResource(id = R.dimen.padding_xsmall)) }

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.startItemXsmallPadding() =
    composed { padding(start = dimensionResource(id = R.dimen.padding_xsmall)) }

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.endIItemXsmallPadding() =
    composed { padding(end = dimensionResource(id = R.dimen.padding_xsmall)) }

/////////////////// Small Padding ///////////////////

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.topItemSmallPadding() =
    composed { padding(top = dimensionResource(id = R.dimen.padding_small)) }

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.startItemSmallPadding() =
    composed { padding(start = dimensionResource(id = R.dimen.padding_small)) }

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.endIItemSmallPadding() =
    composed { padding(end = dimensionResource(id = R.dimen.padding_small)) }


/////////////////// Medium Padding ///////////////////

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.topItemPadding() =
    composed { padding(top = dimensionResource(id = R.dimen.padding_medium)) }

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.bottomItemPadding() =
    composed { padding(bottom = dimensionResource(id = R.dimen.padding_medium)) }

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.startItemPadding() =
    composed { padding(start = dimensionResource(id = R.dimen.padding_medium)) }

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.endItemPadding() =
    composed { padding(end = dimensionResource(id = R.dimen.padding_medium)) }

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.horizontalItemPadding() =
    composed { padding(horizontal = dimensionResource(id = R.dimen.padding_medium)) }

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.verticalItemPadding() =
    composed { padding(vertical = dimensionResource(id = R.dimen.padding_medium)) }


/////////////////// Large Padding ///////////////////

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.topItemLargePadding() =
    composed { padding(top = dimensionResource(id = R.dimen.padding_large)) }

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.bottomItemLargePadding() =
    composed { padding(bottom = dimensionResource(id = R.dimen.padding_large)) }

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.startItemLargePadding() =
    composed { padding(start = dimensionResource(id = R.dimen.padding_large)) }

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.endItemLargePadding() =
    composed { padding(end = dimensionResource(id = R.dimen.padding_large)) }

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.verticalItemLargePadding() =
    composed { padding(vertical = dimensionResource(id = R.dimen.padding_large)) }

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.horizontalItemLargePadding() =
    composed { padding(horizontal = dimensionResource(id = R.dimen.padding_large)) }


/////////////////// X-Large Padding ///////////////////

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.topItemXLargePadding() =
    composed { padding(top = dimensionResource(id = R.dimen.padding_xlarge)) }

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.bottomItemXLargePadding() =
    composed { padding(bottom = dimensionResource(id = R.dimen.padding_xlarge)) }

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.startItemXLargePadding() =
    composed { padding(start = dimensionResource(id = R.dimen.padding_xlarge)) }

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.endItemXLargePadding() =
    composed { padding(end = dimensionResource(id = R.dimen.padding_xlarge)) }

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.verticalItemXLargePadding() =
    composed { padding(vertical = dimensionResource(id = R.dimen.padding_xlarge)) }

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.horizontalItemXLargePadding() =
    composed { padding(horizontal = dimensionResource(id = R.dimen.padding_xlarge)) }

/////////////////// XX-Large Padding ///////////////////

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.topItemXXLargePadding() =
    composed { padding(top = dimensionResource(id = R.dimen.padding_xxlarge)) }

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.bottomItemXXLargePadding() =
    composed { padding(bottom = dimensionResource(id = R.dimen.padding_xxlarge)) }

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.startItemXXLargePadding() =
    composed { padding(start = dimensionResource(id = R.dimen.padding_xxlarge)) }

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.endItemXXLargePadding() =
    composed { padding(end = dimensionResource(id = R.dimen.padding_xxlarge)) }

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.verticalItemXXLargePadding() =
    composed { padding(vertical = dimensionResource(id = R.dimen.padding_xxlarge)) }

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.horizontalItemXXLargePadding() =
    composed { padding(horizontal = dimensionResource(id = R.dimen.padding_xxlarge)) }

fun Modifier.actionBtnBottomPadding() =
    then(this.padding(bottom = 50.dp))
