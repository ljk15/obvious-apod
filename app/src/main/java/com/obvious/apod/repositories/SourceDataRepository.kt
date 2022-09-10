package com.obvious.apod.repositories

import android.app.Application
import android.util.Log
import com.obvious.apod.R
import com.obvious.apod.models.ImageDataModel
import com.obvious.apod.models.ResponseListener
import com.obvious.apod.utils.JsonHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object SourceDataRepository {

    fun readSourceData(
        application: Application,
        listener: ResponseListener<List<ImageDataModel>>,
    ) {
        listener.toggleLoading(true)
        CoroutineScope(Dispatchers.Default).launch {
            try {
                JsonHelper.readRawResJson(application.applicationContext, R.raw.data)
            } catch (e: Exception) {
                if (e.message != null) {
                    listener.onError(e.message.toString())
                }
            }
            listener.toggleLoading(false)
        }
    }

}