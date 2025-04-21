package com.telda.movieapp.utils

import android.os.Parcelable

fun <T> isLoadingState(screenState: ScreenState<T>) =
    screenState.isLoading

fun <T> isFailureState(screenState: ScreenState<T>) =
    !screenState.isLoading && screenState.error.isNotEmpty()

fun <T> isEmptyState(screenState: ScreenState<T>) =
    !screenState.isLoading && screenState.items?.isEmpty() == true

fun isLoadingMoreState(screenState: ScreenState<out Parcelable?>) =
    screenState.isLoading && screenState.items.isNullOrEmpty().not()

fun <T> isSuccessDataState(screenState: ScreenState<T>) =
    !screenState.isLoading && screenState.items.isNullOrEmpty().not()

fun isSuccessDataStateForObject(screenState: ScreenState<out Parcelable?>) =
    !screenState.isLoading && screenState.item != null

