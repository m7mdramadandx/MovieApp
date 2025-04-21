package com.telda.movieapp.utils

import java.text.SimpleDateFormat
import java.util.Locale

fun String.formatDate(): String {
    return try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
        val date = inputFormat.parse(this)
        outputFormat.format(date ?: this)
    } catch (e: Exception) {
        this
    }
}