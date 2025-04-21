package com.telda.movieapp.features.home.domain.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class ProductionCountry(
    @SerializedName("iso_3166_1") val iso31661: String?,
    @SerializedName("name") val name: String?
) : Parcelable