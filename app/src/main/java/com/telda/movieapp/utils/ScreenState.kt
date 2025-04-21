package com.telda.movieapp.utils

data class ScreenState<T>(
    val searchKeyword: String = "",
    val isLoading: Boolean = true,
    val isLoadingMore: Boolean = true,
    val items: List<T>? = listOf(),
    val item: T? = null,
    val error: String = "",
)