package com.obvious.apod.utils

import com.google.gson.Gson

object Injector {
    fun provideGson(): Gson = Gson()
}