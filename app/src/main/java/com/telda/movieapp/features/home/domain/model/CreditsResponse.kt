package com.telda.movieapp.features.home.domain.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class CreditsResponse(
    @SerializedName("cast") val cast: List<Cast?>?,
    @SerializedName("crew") val crew: List<Crew?>?,
    @SerializedName("id") val id: Int?
) : Parcelable