package com.telda.movieapp.features.home.domain.payload

data class SearchPayload(
    val page: Int,
    val query: String,
) {
    companion object {
        val defaults = SearchPayload(
            page = 1,
            query = ""
        )
    }
}