package com.telda.movieapp.features.home.domain.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class PopularMoviesResponse(
    @SerializedName("page") val page: Int?,
    @SerializedName("results") val movies: List<Movie?>?,
    @SerializedName("total_pages") val totalPages: Int?,
    @SerializedName("total_results") val totalResults: Int?
) : Parcelable