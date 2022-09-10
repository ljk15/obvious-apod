package com.obvious.apod.models

interface ResponseListener<T> {
    fun toggleLoading(show: Boolean)
    fun onSuccess(sourceData: T)
    fun onError(error: String)
}