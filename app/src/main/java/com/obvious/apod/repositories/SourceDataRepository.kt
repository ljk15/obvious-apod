package com.obvious.apod.repositories

import android.app.Application
import com.obvious.apod.R
import com.obvious.apod.listeners.ResponseListener
import com.obvious.apod.models.ImageDataModel
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
                val dataList = JsonHelper.readRawResJson(application.applicationContext, R.raw.data)
                if (dataList.isNullOrEmpty())
                    listener.onError("No data to display")
                else
                    listener.onSuccess(dataList)
            } catch (e: Exception) {
                if (e.message != null) {
                    listener.onError("Error: ${e.message.toString()}")
                }
            }
            listener.toggleLoading(false)
        }
    }

}