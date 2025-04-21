package com.telda.movieapp.features.home.domain.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class SpokenLanguage(
    @SerializedName("english_name") val englishName: String?,
    @SerializedName("iso_639_1") val iso6391: String?,
    @SerializedName("name") val name: String?
) : Parcelable