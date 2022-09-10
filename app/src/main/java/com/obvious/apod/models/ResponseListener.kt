package com.obvious.apod.models

interface ResponseListener<T> {
    fun toggleLoading(show: Boolean)
    fun onResponse(sourceData: T)
    fun onError(error: String)
}