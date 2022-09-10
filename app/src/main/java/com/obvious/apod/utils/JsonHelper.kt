package com.obvious.apod.utils

import android.content.Context
import com.google.gson.reflect.TypeToken
import com.obvious.apod.models.ImageDataModel

object JsonHelper {

    @Throws(Exception::class)
    fun readRawResJson(context: Context, resourceId: Int): String? {
        val json = context.resources.openRawResource(resourceId)
            .bufferedReader().use { it.readText() }
        val type = object : TypeToken<List<ImageDataModel>>() {}.type
        val gson = Injector.provideGson()
        return gson.fromJson(json, type)
    }

}