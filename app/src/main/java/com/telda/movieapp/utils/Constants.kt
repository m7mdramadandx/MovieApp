package com.telda.movieapp.utils

object Constants {

    const val STATUS = "status"
    const val SUCCESS = "success"
    const val SESSION_ID = "sessionId"
    const val SERVICE_CODE = "serviceCode"
    const val SERVICE_DATA_LIST = "serviceDataList"
    const val BILLS = "bills"
    const val FEES = "fees"
    const val AMOUNT = "amount"
    const val MOVIE_ID = "movieId"
    const val URL = "url"
    const val TITLE = "title"
    const val ID = "id"

    val headers = hashMapOf(
        "Accept" to "application/json",
//        "Accept-Encoding" to "gzip, deflate, br",
        "Content-Type" to "application/json"
    )

    val BasicAuthHeaders = headers.plus(
        hashMapOf("Authorization" to "Basic YWRtaW46YWRtaW4=")
    )

}
