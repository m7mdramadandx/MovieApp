package com.telda.movieapp.features.home.domain.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class Crew(
    @SerializedName("adult") val adult: Boolean?,
    @SerializedName("credit_id") val creditId: String?,
    @SerializedName("department") val department: String?,
    @SerializedName("gender") val gender: Int?,
    @SerializedName("id") val id: Int?,
    @SerializedName("job") val job: String?,
    @SerializedName("known_for_department") val knownForDepartment: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("original_name") val originalName: String?,
    @SerializedName("popularity") val popularity: Double?,
    @SerializedName("profile_path") val profilePath: String?
) : Parcelable