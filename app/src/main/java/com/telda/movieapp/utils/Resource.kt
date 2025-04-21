package com.telda.movieapp.utils


data class Resource<T>(
    val status: StatusType,
    var data: T? = null,
    val message: String = ""
) {
    fun isIdle(): Boolean = status == StatusType.IDLE
    fun isLoading(): Boolean = status == StatusType.LOADING
    fun isLoadingMore(): Boolean = status == StatusType.LOADING_MORE
    fun isError(): Boolean = status == StatusType.ERROR
    fun isSuccess(): Boolean = status == StatusType.SUCCESS

    companion object {
        fun <T> idle(): Resource<T> = Resource(status = StatusType.IDLE)
        fun <T> loading(): Resource<T> = Resource(status = StatusType.LOADING)
        fun <T> loadingMore(): Resource<T> = Resource(status = StatusType.LOADING_MORE)
        fun <T> success(data: T): Resource<T> = Resource(status = StatusType.SUCCESS, data = data)
        fun <T> error(message: String): Resource<T> =
            Resource(status = StatusType.ERROR, message = message)
    }
}

enum class StatusType {
    IDLE,
    LOADING,
    LOADING_MORE,
    ERROR,
    SUCCESS,
}