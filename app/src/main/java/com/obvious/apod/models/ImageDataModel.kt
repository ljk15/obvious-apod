package com.obvious.apod.models

import com.google.gson.annotations.SerializedName

data class ImageDataModel(
    @SerializedName("copyright")
    var copyright: String?,
    @SerializedName("date")
    var date: String?,
    @SerializedName("explanation")
    var explanation: String?,
    @SerializedName("hdurl")
    var hdUrl: String?,
    @SerializedName("media_type")
    var mediaType: String?,
    @SerializedName("service_version")
    var serviceVersion: String?,
    @SerializedName("title")
    var title: String?,
    @SerializedName("url")
    var url: String?,
)